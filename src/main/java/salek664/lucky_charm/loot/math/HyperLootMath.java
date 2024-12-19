package salek664.lucky_charm.loot.math;

import org.ejml.data.FMatrixRMaj;
import org.ejml.dense.row.CommonOps_FDRM;

public class HyperLootMath {
    public static float[] calculateLuckDelta(float[] qVector) {
        int N = qVector.length;
        switch (N) {
            case 0 -> {
                return new float[0];
            }
            case 1 -> {
                return new float[] {0};
            }
            case 2 -> {
                return new float[] {-1, 1};
            }
            default -> {//TODO hardcode cases N = 3, N = 4, N = 5 like with N = 2 for speed
                float[] result = new float[N];
                float[][] solutionSpace = new float[N - 1][N];
                //Calculate solution vectors
                solutionSpace[0][N - 1] = 1;
                solutionSpace[0][N - 2] = 1;
                for (int i = 1; i < N - 1; i++) {
                    solutionSpace[0][i - 1] = 1;
                    solutionSpace[i][i - 1] = qVector[N - 2] - qVector[N - 1];
                    solutionSpace[i][N - 1] = qVector[i - 1] - qVector[N - 2];
                    solutionSpace[i][N - 2] = qVector[N - 1] - qVector[i - 1];
                }
                generalizedCrossProduct(solutionSpace, result, N);
                return result;
            }
        }
    }
    public static void generalizedCrossProduct(float[][] spaceBasis, float[] destination, int dimension) {
        //Basically an orthogonal complement calculator given N - 1 row vectors in an N dimensional space
        float[] subMatrixArray = new float[(dimension - 1) * (dimension - 1)];
        int position = 0;
        for (int matrixNumber = 0; matrixNumber < dimension; matrixNumber++) {
            //Create submatrix
            for (int row = 0; row < dimension - 1; row++) {
                System.arraycopy(spaceBasis[row], 0, subMatrixArray, position, matrixNumber);
                System.arraycopy(spaceBasis[row], matrixNumber + 1, subMatrixArray, position + matrixNumber, dimension - matrixNumber - 1);
                position += dimension - 1;
            }
            if (matrixNumber % 2 == 0) {
                destination[matrixNumber] = CommonOps_FDRM.det(new FMatrixRMaj(dimension - 1, dimension - 1, true, subMatrixArray));
            } else {
                destination[matrixNumber] = (-1) * CommonOps_FDRM.det(new FMatrixRMaj(dimension - 1, dimension - 1, true, subMatrixArray));
            }
            position = 0;
            subMatrixArray = new float[(dimension - 1) * (dimension - 1)];
        }
    }
    public static void sanityCheck(Object o) {
        o = o;
    }
}
