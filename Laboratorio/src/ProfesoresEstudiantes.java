import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfesoresEstudiantes {


    ArrayList<String> listaProfesores;
    ArrayList<String> listaEstudiantes;
    ArrayList<ArrayList<String>> listaGeneralEstudiantes;
    ArrayList<ArrayList<String>> listaFinalProfesores;
    ArrayList<ArrayList<String>> listaFinalEstudiantes;



    public ProfesoresEstudiantes(){

        listaProfesores= new ArrayList();
        listaGeneralEstudiantes= new ArrayList();

    }
    public void iniciar(){
        int n=0;
        String msj="<<SISTEMA DE REGISTRO>>\n\n";
        msj+="-----------------------------------------\n";
        msj+="1.Registrar Profesores\n";
        msj+="2.Registrar Etudiantes\n";
        msj+="3.Consultar Lista Profesores Y Estudiantes\n";
        msj+="4.Consultar Profesor Por Nombre\n";
        msj+="5.Consulta Cantidad Estudiantes Por Profesor\n";
        msj+="6.Consultar Estudiantes\n";
        msj+="7.salir\n\n";
        msj+="Ingrese una opción!";

        do {
            try {
                String input = JOptionPane.showInputDialog(msj);
                if (input == null) break; //si el usuario presiona cancelar
                n = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "por favor ingrese un número válido");
                continue;
            }

            switch (n) {
                case 1:
                    registrarProfesores();
                    break;
                case 2:
                    if (listaProfesores.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: primero debe registrar a los profesores" + JOptionPane.WARNING_MESSAGE);
                    } else {
                        registrarEstudiantes();
                    }
                    break;
                case 3:
                    if (validarListasLlenas()) ConsultarListaProfesoresYEstudiantes();
                    break;
                case 4:
                    if (validarListasLlenas()) consultaProfesorPorNombre();
                    break;
                case 5:
                    if(validarListasLlenas())consultaCantidadEstudiantesPorProfesor();
                    break;
                case 6:
                    if(validarListasLlenas())consultarEstudiantes();
                    break;
                case 7:
                    System.out.println("¡FINALIZANDO EL SISTEMA!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opción inválida. Intente nuevamente"+JOptionPane.WARNING_MESSAGE);
                    break;
            }

        }while (n!=7);

    }
/* se crea metodo adicional para no repetir la validacion de los casos 3,4,5 & 6*/
    private boolean validarListasLlenas() {
        if (listaProfesores.isEmpty()){
            JOptionPane.showMessageDialog(null,"Error: Primero debe registrar los profesores");
        }
        if (listaEstudiantes.isEmpty()){
            JOptionPane.showMessageDialog(null,"Error: Primero debe registrar los estudiantes");
            return false;
        }
        return true;

    }


    private void registrarProfesores() {

        System.out.println("\n<<REGISTRO DE PROFESORES>>");
        int cant=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de profesores "));
        for (int i=0;i<cant;i++) {
            /*
            Modifique el código para que al momento del registro
             de profesores y estudiantes, para que no permita registrar
              nombres ya existentes dentro de las listas
             */

                String nombreProfesor = JOptionPane.showInputDialog("Ingrese el nombre del profesor " + (i + 1));

                if(listaProfesores.contains(nombreProfesor)){
                    System.out.println("el nombre de ese profesor ya existe");
                    i--;
                }else{
                    listaProfesores.add(nombreProfesor);
                    System.out.println("Registro de: "+nombreProfesor+" Exitoso!");

                }
        }
    }
    private void registrarEstudiantes(){
        System.out.println("\n<<REGISTRO DE ESTUDIANTES>>");
        for (int i=0;i<listaProfesores.size();i++){

            JOptionPane.showMessageDialog(null,"ingrese los estudiantes" + " para el profesor "+listaProfesores.get(i));

            listaEstudiantes=new ArrayList<String>();

            /*
            Modifique el código para que al momento del registro
             de profesores y estudiantes, para que no permita registrar
              nombres ya existentes dentro de las listas
             */


          int cant=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de estudiantes "));

          for (int j=0;j<cant;j++){

              String nombreEst=JOptionPane.showInputDialog("nombre del estudiante "+(j+1));

              boolean existe=false;

              for (int k=0;k<listaGeneralEstudiantes.size();k++) {

                  if (listaGeneralEstudiantes.get(k).contains(nombreEst)) {
                      existe = true;
                      break;
                  }
              }
              if(listaEstudiantes.contains(nombreEst)){

                  existe=true;

              }   if(existe){
                      System.out.println("el nombre de ese estudiante ya existe");
                      j--;
                  }else{
                          listaEstudiantes.add(nombreEst);
                          System.out.println("Registro del Estudiante "+nombreEst+" Exitoso!\n");

                      }
              }

          }
          listaGeneralEstudiantes.add(listaEstudiantes);


    }
    private void ConsultarListaProfesoresYEstudiantes(){

        System.out.println("\n<<LISTA DE PROFESORES Y ESTUDIANTES>>");
        ArrayList<String> listaTemporal;

        for (int i=0;i<listaGeneralEstudiantes.size();i++){

            listaTemporal=listaGeneralEstudiantes.get(i);
            System.out.print("Profesor: "+listaProfesores.get(i));
            System.out.print("[");
            for (int j=0;j<listaTemporal.size();j++){

                System.out.print(listaTemporal.get(j));

                if (j<listaTemporal.size()-1){
                    System.out.print(",");
                }
            }
            System.out.print("]\n");
        }
    }
    private void consultaProfesorPorNombre(){
        System.out.println("\n<<CONSULTAR PROFESOR POR NOMBRE>>");
        String nombre=JOptionPane.showInputDialog("Ingrese el nombre del profesor");

        if (listaProfesores.contains(nombre)){
            int posicion=listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal=listaGeneralEstudiantes.get(posicion);

            System.out.print("Profesor: "+nombre+" = ");
            System.out.print("[");

            for (int j=0;j<listaEstudiantesTemporal.size();j++){

                System.out.print(listaEstudiantesTemporal.get(j));

                if (j<listaEstudiantesTemporal.size()-1){

                    System.out.print(",");

                }

            }
            System.out.print("]\n");
        } else{
            System.out.println("No existe el nombre del profesor "+nombre+"\n");
        }
    }
    private void consultaCantidadEstudiantesPorProfesor(){

        System.out.println("\n<<CONSULTA LA CANTIDAD DE ESTUDIANTES DE PROFESORES>>");
        String nombre=JOptionPane.showInputDialog("Ingrese el nombre del profesor para saber"+"la cantidad de asosciados al profesor ");

        if (listaProfesores.contains(nombre)){
            int posicion=listaProfesores.indexOf(nombre);
            ArrayList<String> listaEstudiantesTemporal=listaGeneralEstudiantes.get(posicion);
            System.out.println("la cantidad de estudiantes asociados al profesor "+nombre+ " "+ " es "+listaEstudiantesTemporal.get(posicion));

        }else{
            System.out.println("No se encuentra el profesor "+nombre+"\n");
        }
    }

    private void consultarEstudiantes() {

        System.out.println("\n<<CONSULTA DE ESTUDIANTES>>\n");
        ArrayList<String> listaTemporal;

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar");

        for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {

            listaTemporal = listaGeneralEstudiantes.get(i);

            if (listaTemporal.contains(nombre)) {

                for (int j = 0; j < listaTemporal.size(); j++) {

                    if (listaTemporal.get(j).equalsIgnoreCase(nombre)) {
                        System.out.println("Encuentra al nombre: " + nombre + " pertenece al " + "grupo de " + listaProfesores.get(i));

                    }

                }


            } else {
                System.out.println("No se encuentra el nombre del estudiante en el grupo de" + listaProfesores.get(i));
            }
        }

    }
}
