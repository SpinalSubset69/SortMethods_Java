package logic;

public class SortMethods {

    public int[] SortMethod(int[] array, String sortMethod){
        switch (sortMethod){
            case "Método Burbuja":
                array = BubleSort(array);
                break;
            case "Método Insertion":
                array = InsertionSort(array);
                break;
            case "Método Selection":
                array = SelectionSort(array);
                break;
            case "Método Shell":
                array = ShellSort(array);
                break;
            default:
                System.out.println("Bad Option");
        }

        return array;
    }

    public int[] BubleSort(int[] array){
        int aux;

        for (int i = 0; i < array.length; i++){
            for(int j= 0; j < array.length - 1; j++){
                if(array[j] > array[j + 1]){
                    aux = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = aux;
                }
            }
        }
        return array;
    }

    public int[] InsertionSort(int [] array){
        int pos , aux;

        for(int i = 0; i < array.length; i ++){
            pos = i; //Se obtiene la posicion actual, esto sirve para recorrer el arreglo de maner descendente
            aux = array[i]; //Obtenemos el dato que estaremos comparando en todo el recorrido

            //La posición debe ser mayor a 0 para así poder tener datos los cuales comparar
            //Después se verifica si el dato en la posición anterior es mayor al de la posición actual, entonces se hace un cambio
            while((pos > 0) && (array[pos - 1] > aux)){
                array[pos] = array[pos - 1];
                pos --;
            }

            //En caso de que array[pos - 1] ya no sea mayor, se le da el valor de aux a la posicion actual
            //De esta manera el dato que se estuvo comparando queda en el lugar que le corresponde
            array[pos] = aux;
        }
        return array;
    }

    public int[] SelectionSort(int[] array){
        int aux, min;
        for(int i = 0; i < array.length; i++){
             min = i; //Posición del elemento inicial, se asume que es el minimo al principio del recorrido

            //Este ciclo debe de iniciar un posición despues de la posicion actual
            for(int j = i + 1; j < array.length; j++){
                if(array[j] > array[min]){
                    min = j; //Intercambiamos las posiciones por que J es menor que I, se busca el elemento menor de la lista
                }
            }
            //Intercambiamos la posición actual con el elemento que sea menor, si no se obtuvo un elemento menor, no se realiza cambio
            aux = array[i];
            array[i] = array[min];
            array[min] = aux;
        }
        return array;
    }

    public int[] ShellSort(int[]array){

        int salto, j, k;

        salto = array.length / 2; //Siempre se incia con este salto
        //El salto mínimo es de 1, por que si llega a 0 es por que el arreglo ya esta ordenado
        while(salto > 0){
            for(int i = salto; i < array.length; i++){
                j = i - salto; //Debe valer 0 al principio

                //Este bucle evita que demos vueltas de más
                while (j >= 0){
                    k = j + salto;
                    if(array[j] <= array[k]){ //Par de elementos ordenados
                        j = -1;
                    }else{
                        int aux = array[j];
                        array[j] = array[k];
                        array[k] = aux;
                        j = j -salto;
                    }
                }
            }
            salto = salto / 2;
        }

        return array;
    }
}
