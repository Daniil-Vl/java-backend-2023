package edu.hw9.task3;

import edu.project2.solvers.SolverImplementationTest;

class MultiThreadedDFSSolverTest extends SolverImplementationTest<MultiThreadedDFSSolver> {

    @Override
    protected MultiThreadedDFSSolver createInstance() {
        return new MultiThreadedDFSSolver();
    }
}
