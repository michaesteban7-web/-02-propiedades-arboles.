import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NodoGeneral<T> {
    private T dato;
    private List<NodoGeneral<T>> hijos = new ArrayList<>();

    public NodoGeneral(T dato) { this.dato = dato; }
    public void agregarHijo(NodoGeneral<T> hijo) { hijos.add(hijo); }
    public T getDato() { return dato; }
    public List<NodoGeneral<T>> getHijos() { return hijos; }

    //FORMA DE MUESTRA //
    public void mostrarArbol(String espacio) {
    System.out.println(espacio + dato);

    for (NodoGeneral<T> hijo : hijos) {
        hijo.mostrarArbol(espacio + "    ");
    }
}
public class Main {

    public static void main(String[] args) {

        //Raiz
        NodoGeneral<String> movilidad =new NodoGeneral<>("MOVILIDAD ");

        // nivel #1
        NodoGeneral<String> motos =new NodoGeneral<>("MOTOS");
        NodoGeneral<String> carros=new NodoGeneral<>("CARROS");
        NodoGeneral<String> bicicletas =new NodoGeneral<>("BICICLETAS");
        NodoGeneral<String> transporte_publico =new NodoGeneral<>("TRANSPORTE PUBLICO");
        
        //coneccion nivel #1 con Raiz
        movilidad.agregarHijo(motos);
        movilidad.agregarHijo(carros);
        movilidad.agregarHijo(bicicletas);
        movilidad.agregarHijo(transporte_publico);
        
        // Nivel #2
        //motos
        NodoGeneral<String> electricas =new NodoGeneral<>("ELECTRICAS");
        NodoGeneral<String> de_combus =new NodoGeneral<>("DE COMBUSTION");
        
        //coneccion motos nivel #1 con nivel #2
        motos.agregarHijo(electricas);
        motos.agregarHijo(de_combus);
        
        //carros
        NodoGeneral<String> electrico =new NodoGeneral<>("ELECTRICOS");
        NodoGeneral<String> hibridos =new NodoGeneral<>("HIBRIDOS");
        NodoGeneral<String> combustin =new NodoGeneral<>("DE COMBUSTION");

        //coneccion caros nivel #1 con nivel #2
        carros.agregarHijo(electrico);
        carros.agregarHijo(hibridos);
        carros.agregarHijo(combustin);

        //bicicletas
        NodoGeneral<String> elec =new NodoGeneral<>("ELECTRICAS");
        NodoGeneral<String> manuales =new NodoGeneral<>("MANUALES ");

        //coneccion bicicletas nivel #1 con nivel #2
        bicicletas.agregarHijo(elec);
        bicicletas.agregarHijo(manuales);

        //trasnporte publico
        NodoGeneral<String> bus =new NodoGeneral<>("BUS");
        NodoGeneral<String> taxi =new NodoGeneral<>("TAXI ");

        //coneccion tranporte publico nivel #1 con nivel #2
        transporte_publico.agregarHijo(bus);
        transporte_publico.agregarHijo(manuales);
        
        //nivel #3
        //motos de combustion
        NodoGeneral<String> pulsar =new NodoGeneral<>("PULSAR");
        NodoGeneral<String> akt =new NodoGeneral<>("AKT");
        
        //coneccion de nivel #2 con nivel #3 
        de_combus.agregarHijo(pulsar);
        de_combus.agregarHijo(akt);

        //mostrar arbol
        System.out.println("=== ÁRBOL MOVILIDAD ===");
        MetricasArbol.mostrarArbol(movilidad, 0);
        
        //propiedades generales
        System.out.println();
        System.out.println("=== PROPIEDADES DEL ÁRBOL ===");
        System.out.println();
        
        System.out.println("Raíz: " + movilidad.getDato());
        System.out.println("Número total de nodos: "+ MetricasArbol.contarNodos(movilidad));
        System.out.println("Número de hojas: " + MetricasArbol.contarHojas(movilidad));
        System.out.println("Grado del árbol: "+ MetricasArbol.gradoArbol(movilidad));
        System.out.println("Altura del árbol: "+ MetricasArbol.altura(movilidad));
        System.out.println("Número de niveles: " + (MetricasArbol.altura(movilidad) + 1));
        
        //profundidad de nodos 
        System.out.println();
        System.out.println("=== PROFUNDIDAD DE CINCO NODOS ===");
        System.out.println();

        mostrarProfundidad(movilidad, motos);
        mostrarProfundidad(movilidad, carros);
        mostrarProfundidad(movilidad, bicicletas);
        mostrarProfundidad(movilidad, transporte_publico);
        mostrarProfundidad(movilidad, pulsar);
        
        System.out.println();
        System.out.println("=== GRADO DE ALGUNOS NODOS ===");
        
        mostrarGrado(movilidad);
        mostrarGrado(motos );
        mostrarGrado(carros );
        mostrarGrado(bicicletas);
        mostrarGrado(transporte_publico);

        System.out.println();
        System.out.println("=== TABLA DE MÉTRICAS ===");
        MetricasArbol.mostrarTablaMetricas(movilidad);


        System.out.println();
        System.out.println("=== CAMINOS ===");
            
        mostrarCamino(movilidad, motos);
        mostrarCamino(movilidad, carros);
        mostrarCamino(movilidad, bicicletas);
        mostrarCamino(movilidad, transporte_publico);
        mostrarCamino(movilidad, pulsar);
        mostrarCamino(movilidad, akt);
        mostrarCamino(movilidad, bus);
        mostrarCamino(movilidad, taxi);
       
        
        System.out.println();
        System.out.println("=== COMPARACIÓN ===");
        
        int alturaManual = 3;
        int alturaCalculada =
        MetricasArbol.altura(movilidad);
        
        System.out.println("Altura calculada manualmente: "+ alturaManual);
        System.out.println("Altura calculada por Java: " + alturaCalculada);
        System.out.println(alturaManual == alturaCalculada? "Resultado: COINCIDEN." : "Resultado: REVISAR CÁLCULO.");
    }
        
        private static void mostrarProfundidad(NodoGeneral<?> raiz,NodoGeneral<?> nodo) {
        
            System.out.printf("%-28s -> %d%n",nodo.getDato(),MetricasArbol.profundidad(raiz, nodo));
        
        }
        
        private static void mostrarGrado(NodoGeneral<?> nodo) {
        
            System.out.printf("%-28s -> %d%n",nodo.getDato(),MetricasArbol.gradoNodo(nodo));
        
        }
        
        private static void mostrarCamino(NodoGeneral<?> raiz,NodoGeneral<?> destino) {
        
            var ruta =MetricasArbol.camino(raiz, destino);
            System.out.println(MetricasArbol.rutaComoTexto(ruta));
            System.out.println("Longitud: "+ MetricasArbol.longitudCamino(raiz,destino)+ " aristas");

        }
    }        
       


      


}
