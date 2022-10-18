package main.java.dev.codenmore.juegorpg.input;

import java.awt.*;

public interface Clickable {
    boolean isWithinBounds(Point point);
    void onClick(MouseListenerImpl.MouseButton mouseButton);
    void setHovering(boolean isHovering);
}
