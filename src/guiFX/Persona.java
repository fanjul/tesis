package guiFX;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
	
	
    private final SimpleStringProperty nombre; //Observa de que tipo son estos objetos, es una clase usada para definir propiedades y con ella podemos
    private final SimpleStringProperty apellido;//acceder a los atributos/propiedades/campos de la clase
    private final SimpleStringProperty edad;//en este caso, la propiedad es de tipo String.

    public Persona(String nombre, String apellido, String edad) {
        this.nombre = new SimpleStringProperty(nombre);//Dentro del constructor asignamos el valor a los
        this.apellido = new SimpleStringProperty(apellido);//atributos .
        this.edad = new SimpleStringProperty(edad);
    }

   //Lo que sigue a continuación son los getters, estos serán usados para acceder a los atributos.

    public String getNombre() {
        return nombre.get();
    }

    public String getApellido() {
        return apellido.get();
    }

    public String getEdad() {
        return edad.get();
    }
	
	
}
