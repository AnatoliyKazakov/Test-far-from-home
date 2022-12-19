import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

public class WayToExit {
    private static class DivideByZeroExit implements Runnable {
        @Override
        public void run() {
            final int result = 10 / 0;
            System.out.println(result);
        }
    }

    private static class NullPointerExit implements Runnable {
        @Override
        public void run() {
            final Object Null = null;
            System.out.println(Null.hashCode());
        }
    }

    private static class OutOfBoundaryExit implements Runnable {
        @Override
        public void run() {
            final String[] array = {};
            System.out.println(array[10]);
        }
    }

    private static final List<Runnable> waysToExit = List.of(
      new DivideByZeroExit(), new NullPointerExit(), new OutOfBoundaryExit()
    );

    public void exit() {
        final Random random = new SecureRandom();
        int way = random.nextInt(waysToExit.size());
        waysToExit.get(way).run();
    }
}