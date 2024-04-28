package code.shubham.fiq.models;

public class Message {
    private final String key;
    private final String message;
    private final long processingTimeInSeconds;

    public Message(
            final String key,
            final String message,
            final long processingTimeInSeconds) {
        this.key = key;
        this.message = message;
        this.processingTimeInSeconds = processingTimeInSeconds;
    }

    public static Message of(final String message) {
        final String[] part = message.split(":");
        return new Message(part[0], part[1], Long.parseLong(part[2]));
    }

    public long getProcessingTimeInSeconds() {
        return processingTimeInSeconds;
    }

    @Override
    public String toString() {
        return String.format("%s:%s:%s", this.key, this.message, this.processingTimeInSeconds);
    }
}
