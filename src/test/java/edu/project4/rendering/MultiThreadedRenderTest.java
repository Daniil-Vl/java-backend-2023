package edu.project4.rendering;

public class MultiThreadedRenderTest extends RendererTest {
    @Override
    Renderer getInstance() {
        return new MultiThreadedRenderer();
    }
}
