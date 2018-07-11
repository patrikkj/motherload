package utils;

import entities.Entity;

@FunctionalInterface
public interface Event<T> {
	public void fire(T type, Entity e1, Entity e2);
}
