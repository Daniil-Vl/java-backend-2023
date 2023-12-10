package edu.project4.rendering;

public class SingleThreadRenderTest extends RendererTest {
    @Override
    Renderer getInstance() {
        return new SingleThreadedRenderer();
    }
}
