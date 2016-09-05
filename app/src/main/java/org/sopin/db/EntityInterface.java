package org.sopin.db;


public interface EntityInterface extends HydrateableInterface {

    Integer getId();

    Object clone();
}
