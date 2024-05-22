package moteurJeu;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

public class FrameStats {

    private long frameCount;
    private double meanFrameInterval; // millis
    private final ReadOnlyStringWrapper text = new ReadOnlyStringWrapper(this, "text", "Frame count: 0 Average frame interval: N/A");

    public long getFrameCount() {
        return frameCount;
    }

    public double getMeanFrameInterval() {
        return meanFrameInterval;
    }

    public void addFrame(long frameDurationNanos) {
        meanFrameInterval = (meanFrameInterval * frameCount + frameDurationNanos / 1_000_000.0) / (frameCount + 1);
        frameCount++;
        text.set(toString());
    }

    public String getText() {
        return text.get();
    }

    public ReadOnlyStringProperty textProperty() {
        return text.getReadOnlyProperty();
    }

    @Override
    public String toString() {
        return String.format("Frame count: %,d Average frame interval: %.3f milliseconds", getFrameCount(), getMeanFrameInterval());
    }
}