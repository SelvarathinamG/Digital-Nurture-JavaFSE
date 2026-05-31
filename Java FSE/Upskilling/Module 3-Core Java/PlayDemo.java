public class PlayDemo {
    interface Playable { void play(); }
    static class Guitar implements Playable { public void play() { System.out.println("Guitar: strum"); } }
    static class Piano implements Playable { public void play() { System.out.println("Piano: plink"); } }

    public static void main(String[] args) {
        Playable g = new Guitar();
        Playable p = new Piano();
        g.play();
        p.play();
    }
}

/*
Output:
Guitar: strum
Piano: plink
*/
