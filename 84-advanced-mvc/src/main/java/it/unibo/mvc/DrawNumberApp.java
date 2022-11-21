package it.unibo.mvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private static final String PATH = "src/main/resources/config.yml";
    
    private final File file = new File(PATH);
    private final DrawNumber model;
    private final List<DrawNumberView> views;
    private final ConfigurationReader configuration;

    /**
     * @param views
     *            the views to attach
     * @throws FileNotFoundException
     */
    public DrawNumberApp(final DrawNumberView... views) throws FileNotFoundException {
        configuration = new ConfigurationReader(file);
        /*
        * Side-effect proof
        */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        this.model = new DrawNumberImpl(configuration.MINIMUM,
            configuration.MAXIMUM,
            configuration.ATTEMPTS
        );
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp(new DrawNumberViewImpl(),
            new DrawNumberViewImpl(),
            new PrintStreamView(System.out),
            new PrintStreamView("file.log"));
    }

}
