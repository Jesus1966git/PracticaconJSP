/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package jesus.controlador;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import jesus.modelo.Elemento;

@WebServlet
public class Controlador extends HttpServlet {

    //PROPIEDADES
    @Inject
    private Elemento elemento;
    private List<String> listaTipos;

    //METODOS PROPIOS
    /*
    * Método de carga del desplegable de opciones de uso de los inmuebles en proindiviso
     */
    public void cargaUsos() {
        listaTipos = new ArrayList<>();
        listaTipos.add("Garaje");
        listaTipos.add("Trastero");
        listaTipos.add("Otro uso");
    }

    //METODOS IMPLICITOS DEL SERVLET
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Se implementa la carga dinamica de navegacion de paginas
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Carga dinamica de las paginas jsp
        String opcion = request.getParameter("opcion");
        if (opcion == null) {
            opcion = "datos";
        }
        String pagina = null;
        switch (opcion) {
            case "datos":
                cargaUsos();
                request.setAttribute("listaTipos", listaTipos);
                pagina = "/WEB-INF/datos.jsp";
                break;
            case "resultados":
                pagina = "/WEB-INF/resultados.jsp";
                break;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method. Se realizan los cálculos del
     * porcentaje de la cuota Se realiza la concatenacion del texto resultante
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        elemento.setUso(request.getParameter("uso"));
        elemento.setCuota(request.getParameter("cuota"));
        elemento.setCuota(elemento.getCuota().replace(",", "."));
        elemento.setFinca(request.getParameter("finca"));
        elemento.setNumero(request.getParameter("numero"));

        String resultado = elemento.getCuota();
        DecimalFormat df = new DecimalFormat("#.00000");
        int indice = (int) Integer.valueOf(elemento.getUso());
        if (resultado.contains("/")) {
            String dividendo = resultado.substring(0, resultado.indexOf("/"));
            String divisor = resultado.substring(resultado.indexOf("/") + 1);
            Double division = (Double.valueOf(dividendo) / Double.valueOf(divisor)) * 100;
            elemento.setPorcentaje(df.format(division));
            elemento.setCuota(elemento.getCuota().replace(".", ","));
            elemento.setTexto("El elemento valorado destinado a " + listaTipos.get(indice).toLowerCase()
                    + " corresponde con una cuota indivisa de " + elemento.getCuota()
                    + " partes del total de la finca registral " + elemento.getFinca() + ".");
        } else {
            elemento.setPorcentaje(df.format(Double.valueOf(resultado)));
            elemento.setTexto("El elemento valorado destinado a " + listaTipos.get(indice).toLowerCase()
                    + " corresponde con una cuota indivisa de " + elemento.getCuota()
                    + "% del total de la finca registral " + elemento.getFinca() + ".");
        }
        elemento.setFondo(listaTipos.get(indice).toLowerCase()+".png");

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
