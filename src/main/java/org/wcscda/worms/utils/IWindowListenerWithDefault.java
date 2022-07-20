package org.wcscda.worms.utils;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public interface IWindowListenerWithDefault extends WindowListener {
    @Override
    default void windowOpened(WindowEvent windowEvent) { }

    @Override
    default void windowClosing(WindowEvent windowEvent) { }

    @Override
    default void windowClosed(WindowEvent windowEvent) { }

    @Override
    default void windowIconified(WindowEvent windowEvent) { }

    @Override
    default void windowDeiconified(WindowEvent windowEvent) { }

    @Override
    default void windowActivated(WindowEvent windowEvent) { }

    @Override
    default void windowDeactivated(WindowEvent windowEvent) { }
}
