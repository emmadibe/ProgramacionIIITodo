package lambdas.suplier;

import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        /*
         * 📦 Supplier
         * 💡 No recibe ningún valor, pero retorna un resultado.
         */
        Supplier<Double> suplier = Math::random;
        System.out.println(suplier.get());

    }
}
