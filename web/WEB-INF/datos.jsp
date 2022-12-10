<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Introducción de datos: </title>
    <link rel="stylesheet" type="text/css" href="css/pauta.css"/>
    </head>
    <body>      
       <form action="Controlador" method="POST" >
            <nueva><strong>Por favor rellene el formulario con los datos de la descripción registral (Nota simple):</strong></nueva>
            <table  style="background-color: lightblue">
                <tr>
                    <td>Tipo de elemento: </td>
                    <td><select name="uso">            
                            <c:forEach var="tipos" items="${listaTipos}" varStatus="loop">
                                <option value="${loop.index}">${tipos}</option>
                            </c:forEach>
                        </select> 
                    </td>
                </tr>
                <tr>
                    <td>Identificador del elemento: </td>
                    <td><input type="text" name="numero" required /></td>
                </tr>
                <tr>
                    <td>Cuota indivisa según registro: </td>
                    <td><input type="text" name="cuota" pattern="[0-9]+\.*,*[0-9]*/*[0-9]*\.*,*[0-9]*" required />
                <limitacion> Solo números, coma, punto o barra "/"</limitacion></td>                       
                </tr>
                <tr>
                    <td>Número de finca registral: </td>
                    <td><input type="text" name="finca" required /></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="align-content: end"><button type="submit" name="opcion" value="resultados">Enviar</button></td>
                </tr>
            </table>
            <br/>
            <img src="imagenes/ejemplo.jpg" width="600" />
        </form>
    </body>
</html>
