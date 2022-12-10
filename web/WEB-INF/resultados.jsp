<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Obtención de resultados: </title>
        <link rel="stylesheet" type="text/css" href="css/pauta.css">
    </head>
    <body style="background-image: url(imagenes/${elemento.fondo})">
    <nueva>
        <table style="background-image:  url(imagenes/texture.jpg)">
            <tr>
                <td>La cuota descrita es: </td>    
                <td><h2>${elemento.cuota}</h2></td>
            </tr>
            <tr>
                <td>El porcentaje resultante es: </td>
                <td><strong>${elemento.porcentaje}%</strong></td>    
            </tr>
            <tr>
                <td><label>ADVERTENCIA:</label></td>
                <td><textarea name="textarea" cols="50" rows="2">${elemento.texto}</textarea></td>     
            <tr>
        </table>
    </nueva>
</body>
</html>
