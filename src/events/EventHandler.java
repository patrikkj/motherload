package events;

@FunctionalInterface
public interface EventHandler<T extends Event> {
	public void handle(T t);
}
