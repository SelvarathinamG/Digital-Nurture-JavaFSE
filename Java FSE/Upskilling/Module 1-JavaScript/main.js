console.log('Welcome to the Community Portal');

const fallbackEvents = [
  {
    name: 'Riverside Summer Concert',
    date: '2026-06-20',
    seats: 200,
    category: 'music',
    location: 'Riverside Park',
    description: 'Local bands and family-friendly performances on the riverside stage. Food trucks and picnic space available.'
  },
  {
    name: 'Community Market & Street Food',
    date: '2026-06-13',
    seats: 120,
    category: 'food',
    location: 'Market Square',
    description: 'A weekend market featuring local producers, street food stalls, and kids activities.'
  },
  {
    name: 'Neighborhood Cleanup Drive',
    date: '2026-06-05',
    seats: 0,
    category: 'community',
    location: 'River Walk',
    description: 'Volunteer cleanup to keep the riverbank green — supplies provided. (Event is full)'
  },
  {
    name: 'Baking Basics Workshop',
    date: '2026-06-17',
    seats: 16,
    category: 'workshop',
    location: 'Community Kitchen',
    description: 'Hands-on beginner baking class covering bread and simple pastries. Take home your bakes.'
  },
  {
    name: 'Charity 5K Run',
    date: '2026-07-03',
    seats: 300,
    category: 'community',
    location: 'City Loop',
    description: 'Join the charity run to raise funds for the local shelter. Staggered starts for all fitness levels.'
  },
  {
    name: 'Artisan Night Market',
    date: '2026-06-27',
    seats: 80,
    category: 'market',
    location: 'Old Town Lane',
    description: 'Evening market with handmade crafts, live acoustic music, and warm beverages.'
  }
];

const state = {
  events: [],
  category: 'all',
  location: 'all',
  searchTerm: '',
  totalRegistrations: 0,
  totalTracker: createCategoryTracker(),
  musicTracker: createCategoryTracker(),
  activeRegistrations: new Map()
};

const elements = {
  eventBoard: document.querySelector('#eventBoard'),
  welcomeBanner: document.querySelector('#welcomeBanner'),
  eventInfo: document.querySelector('#eventInfo'),
  availabilityInfo: document.querySelector('#availabilityInfo'),
  categoryFilter: document.querySelector('#categoryFilter'),
  locationFilter: document.querySelector('#locationFilter'),
  quickSearch: document.querySelector('#quickSearch'),
  loadEventsBtn: document.querySelector('#loadEventsBtn'),
  showMusicBtn: document.querySelector('#showMusicBtn'),
  refreshFromApi: document.querySelector('#refreshFromApi'),
  toggleSpinnerBtn: document.querySelector('#toggleSpinnerBtn'),
  spinner: document.querySelector('#loadingSpinner'),
  registrationForm: document.querySelector('#registrationForm'),
  selectedEvent: document.querySelector('select[name="selectedEvent"]'),
  formMessage: document.querySelector('#formMessage'),
  totalRegistrations: document.querySelector('#totalRegistrations'),
  musicRegistrations: document.querySelector('#musicRegistrations'),
  visibleEvents: document.querySelector('#visibleEvents'),
  jqueryCard: document.querySelector('#jqueryCard'),
  jqueryToggle: document.querySelector('#jqueryToggle')
};

function CommunityEvent(data) {
  const {
    name,
    date,
    seats = 0,
    category = 'community',
    location = 'Unknown location',
    description = ''
  } = data;

  this.id = `${name.toLowerCase().replace(/[^a-z0-9]+/g, '-')}-${date}`;
  this.name = name;
  this.date = date;
  this.seats = seats;
  this.category = category;
  this.location = location;
  this.description = description;
  this.registered = false;
}

CommunityEvent.prototype.checkAvailability = function () {
  const today = new Date();
  today.setHours(0, 0, 0, 0);
  const eventDate = new Date(`${this.date}T00:00:00`);
  return eventDate >= today && this.seats > 0;
};

function createCategoryTracker(initialValue = 0) {
  let count = initialValue;

  return {
    increment(step = 1) {
      count += step;
      return count;
    },
    decrement(step = 1) {
      count = Math.max(0, count - step);
      return count;
    },
    value() {
      return count;
    }
  };
}

function wait(ms = 1000) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

function titleCase(value = '') {
  return value.charAt(0).toUpperCase() + value.slice(1);
}

function addEvent(eventData) {
  const event = eventData instanceof CommunityEvent ? eventData : new CommunityEvent(eventData);
  state.events.push(event);
  return event;
}

function filterEventsByCategory(category, callback = () => true) {
  return [...state.events].filter((event) => {
    const matchesCategory = category === 'all' || event.category === category;
    return matchesCategory && callback(event);
  });
}

function registerUser(event, source = 'card') {
  if (!event.checkAvailability()) {
    throw new Error('This event is full or already finished.');
  }

  event.seats--;
  event.registered = true;
  state.totalRegistrations += 1;
  state.totalTracker.increment();

  if (event.category === 'music') {
    state.musicTracker.increment();
  }

  state.activeRegistrations.set(event.id, true);
  renderEventBoard();

  if (source === 'form') {
    elements.formMessage.textContent = `Registered for ${event.name}. A confirmation was queued.`;
  } else {
    elements.formMessage.textContent = `You registered for ${event.name}.`;
  }
}

function cancelRegistration(event) {
  if (!state.activeRegistrations.has(event.id)) {
    return;
  }

  event.seats++;
  event.registered = false;
  state.totalRegistrations = Math.max(0, state.totalRegistrations - 1);
  state.totalTracker.decrement();
  if (event.category === 'music') {
    state.musicTracker.decrement();
  }
  state.activeRegistrations.delete(event.id);
  renderEventBoard();
  elements.formMessage.textContent = `Registration cancelled for ${event.name}.`;
}

function createEventCard(event) {
  const card = document.createElement('article');
  card.className = 'event-card';
  card.dataset.eventId = event.id;

  const title = `${titleCase(event.category)} on ${event.name}`;
  const availability = event.checkAvailability() ? 'Open for registration' : 'Closed';
  const buttonText = state.activeRegistrations.has(event.id) ? 'Cancel' : 'Register';

  card.innerHTML = `
    <h3>${title}</h3>
    <p>${event.description}</p>
    <div class="meta">
      <span><strong>Date:</strong> ${event.date}</span>
      <span><strong>Location:</strong> ${event.location}</span>
      <span><strong>Seats:</strong> ${event.seats}</span>
      <span><strong>Status:</strong> ${availability}</span>
    </div>
  `;

  const actions = document.createElement('div');
  actions.className = 'actions';

  const button = document.createElement('button');
  button.type = 'button';
  button.textContent = buttonText;
  button.onclick = () => {
    try {
      if (state.activeRegistrations.has(event.id)) {
        cancelRegistration(event);
      } else {
        registerUser(event);
      }
    } catch (error) {
      elements.formMessage.textContent = error.message;
    }
  };

  actions.appendChild(button);
  card.appendChild(actions);

  return card;
}

function renderEventDetails(event) {
  if (!event) {
    elements.eventInfo.textContent = 'No event loaded yet.';
    elements.availabilityInfo.textContent = 'Waiting for data.';
    return;
  }

  const details = Object.entries(event).map(([key, value]) => `${key}: ${value}`).join(' | ');
  elements.eventInfo.textContent = details;
  elements.availabilityInfo.textContent = event.checkAvailability() ? 'This event is open.' : 'This event is closed.';
}

function renderSelectedEventOptions() {
  const options = state.events
    .filter((event) => event.checkAvailability() || state.activeRegistrations.has(event.id))
    .map((event) => `<option value="${event.id}">${event.name} - ${event.location}</option>`);

  elements.selectedEvent.innerHTML = options.join('');
}

function renderEventBoard() {
  const workingList = [...state.events];
  const filtered = filterEventsByCategory(state.category, (event) => {
    const locationMatch = state.location === 'all' || event.location.toLowerCase() === state.location;
    const searchMatch = !state.searchTerm || event.name.toLowerCase().includes(state.searchTerm);
    const upcomingAndOpen = event.checkAvailability();

    if (!upcomingAndOpen) {
      return false;
    }

    if (!locationMatch || !searchMatch) {
      return false;
    }

    return true;
  });

  elements.eventBoard.innerHTML = '';

  if (!filtered.length) {
    elements.eventBoard.innerHTML = '<p class="status">No upcoming events match your filter.</p>';
  } else {
    filtered.forEach((event) => {
      elements.eventBoard.appendChild(createEventCard(event));
    });
  }

  elements.visibleEvents.textContent = filtered.length;
  elements.totalRegistrations.textContent = state.totalRegistrations;
  elements.musicRegistrations.textContent = state.musicTracker.value();
  renderEventDetails(filtered[0] || workingList[0]);
  renderSelectedEventOptions();
}

function applyFilters() {
  state.category = elements.categoryFilter.value;
  state.location = elements.locationFilter.value;
  renderEventBoard();
}

function setSearchTerm() {
  state.searchTerm = elements.quickSearch.value.trim().toLowerCase();
  renderEventBoard();
}

function populateFormWithFirstEvent() {
  if (!state.events.length) {
    return;
  }

  const firstOpenEvent = state.events.find((event) => event.checkAvailability());
  if (firstOpenEvent) {
    elements.selectedEvent.value = firstOpenEvent.id;
  }
}

function setBannerMessage() {
  const firstOpenEvent = state.events.find((event) => event.checkAvailability());
  if (!firstOpenEvent) {
    elements.welcomeBanner.textContent = 'No open events are available right now.';
    return;
  }

  elements.welcomeBanner.textContent = `Today’s focus: ${firstOpenEvent.name} at ${firstOpenEvent.location}.`;
}

function syncStateFromData(data) {
  state.events = [];
  data.forEach((eventData) => addEvent(eventData));
  renderEventBoard();
  populateFormWithFirstEvent();
  setBannerMessage();
}

function showSpinner(message) {
  elements.spinner.classList.remove('hidden');
  elements.formMessage.textContent = message;
}

function hideSpinner() {
  elements.spinner.classList.add('hidden');
}

function loadEventsWithThen() {
  showSpinner('Loading events from the mock JSON file...');

  return fetch('events.json')
    .then((response) => {
      if (!response.ok) {
        throw new Error('Unable to read the mock JSON file.');
      }
      return response.json();
    })
    .then((data) => {
      syncStateFromData(data);
      elements.formMessage.textContent = 'Events loaded from the JSON file.';
    })
    .catch(() => {
      syncStateFromData(fallbackEvents);
      elements.formMessage.textContent = 'Using fallback events because the JSON file could not be fetched directly.';
    })
    .finally(() => {
      hideSpinner();
    });
}

async function loadEventsWithAsyncAwait() {
  showSpinner('Fetching events with async / await...');

  try {
    const response = await fetch('events.json');
    if (!response.ok) {
      throw new Error('Bad response from mock JSON endpoint.');
    }
    const data = await response.json();
    syncStateFromData(data);
    elements.formMessage.textContent = 'Async load finished with the JSON endpoint.';
  } catch (error) {
    syncStateFromData(fallbackEvents);
    elements.formMessage.textContent = 'Async load used the fallback event list.';
  } finally {
    hideSpinner();
  }
}

function simulateLoading() {
  showSpinner('Simulating a delayed response...');
  wait(1200).then(() => {
    hideSpinner();
    elements.formMessage.textContent = 'Loading finished after a small timeout.';
  });
}

async function postRegistration(payload) {
  try {
    const response = await fetch('https://jsonplaceholder.typicode.com/posts', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    });

    if (!response.ok) {
      throw new Error('Mock API rejected the request.');
    }

    return await response.json();
  } catch (error) {
    return {
      offline: true,
      ...payload
    };
  }
}

function validateForm(form) {
  const { name, email, selectedEvent } = form.elements;
  let valid = true;

  document.querySelectorAll('.error').forEach((errorNode) => {
    errorNode.textContent = '';
  });

  if (!name.value.trim()) {
    document.querySelector('[data-error-for="name"]').textContent = 'Name is required.';
    valid = false;
  }

  if (!email.value.trim() || !email.value.includes('@')) {
    document.querySelector('[data-error-for="email"]').textContent = 'Enter a valid email.';
    valid = false;
  }

  if (!selectedEvent.value) {
    document.querySelector('[data-error-for="selectedEvent"]').textContent = 'Choose an event first.';
    valid = false;
  }

  return valid;
}

async function handleFormSubmit(event) {
  event.preventDefault();

  const form = event.currentTarget;
  if (!validateForm(form)) {
    elements.formMessage.textContent = 'Fix the highlighted fields before submitting.';
    return;
  }

  const { name, email, selectedEvent, notes } = form.elements;
  const chosenEvent = state.events.find((entry) => entry.id === selectedEvent.value);

  if (!chosenEvent) {
    elements.formMessage.textContent = 'That event is no longer available.';
    return;
  }

  const payload = {
    name: name.value.trim(),
    email: email.value.trim(),
    event: chosenEvent.name,
    notes: notes.value.trim()
  };

  elements.formMessage.textContent = 'Submitting registration...';
  await wait(1000);

  const result = await postRegistration(payload);
  try {
    registerUser(chosenEvent, 'form');
  } catch (error) {
    elements.formMessage.textContent = error.message;
    return;
  }

  if (result.offline) {
    elements.formMessage.textContent = `Saved locally for ${payload.name}. The mock API was not reachable, so the portal kept the registration on the page.`;
  } else {
    elements.formMessage.textContent = `Success. ${payload.name} is registered for ${payload.event}.`;
  }
}

function wireEvents() {
  elements.categoryFilter.onchange = applyFilters;
  elements.locationFilter.onchange = applyFilters;
  elements.quickSearch.addEventListener('keydown', (event) => {
    if (event.key === 'Enter') {
      setSearchTerm();
    }
  });

  elements.loadEventsBtn.onclick = loadEventsWithThen;
  elements.showMusicBtn.onclick = () => {
    elements.categoryFilter.value = 'music';
    applyFilters();
  };
  elements.refreshFromApi.onclick = loadEventsWithAsyncAwait;
  elements.toggleSpinnerBtn.onclick = simulateLoading;
  elements.registrationForm.onsubmit = handleFormSubmit;

  if (window.jQuery) {
    $('#jqueryToggle').click(() => {
      if ($('#jqueryCard').is(':visible')) {
        $('#jqueryCard').fadeOut(220);
      } else {
        $('#jqueryCard').fadeIn(220);
      }
    });
  } else {
    elements.jqueryToggle.onclick = () => {
      elements.jqueryCard.classList.toggle('hidden');
    };
  }
}

function bootstrap() {
  wireEvents();
  loadEventsWithThen();
}

window.addEventListener('load', () => {
  alert('The page is fully loaded.');
});

document.addEventListener('DOMContentLoaded', bootstrap);
