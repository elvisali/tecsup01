<%-- 
    Document   : editarAreas
    Created on : 29-oct-2020, 18:51:59
    Author     : Banks
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" 
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
              crossorigin="anonymous">
    </head>
    <body>
    <div class="card" style="width: 50rem;padding: 30px 0px 0px 30px;">
        <h4 class="display-8"><c:out value='${titulo}'/></h4>
        <form name=mod_opcion method=POST action="/WebAppSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="GRABAR_AREA">
            <input type=hidden name=operacion value='<c:out value='${operacion}'/>'>
            <input type=hidden name=xcod value="<c:out value='${registro[0]}'/>">
            <table class="table table-striped table-hover">
            <tbody>
            <tr><th>Codigo: </th>
            <td><c:out value='${registro[0]}'/> </td> </tr>
            <tr><th>Nombre: </th>
            <td><input name=xnom value="<c:out value='${registro[1]}'/>"
                size=40></td></tr>
            <tr><th>Abreviatura: </th>
            <td><input name=xabr value="<c:out value='${registro[2]}'/>" 
                size=15></td></tr>
            <tr><th>Estado:</th>
            <td><c:choose>
                  <c:when test="${registro[3]=='X'}">
                      <input type=radio name=xest value=A>Activo
                      <input type=radio name=xest value=X checked>Inactivo
                  </c:when>
                  <c:otherwise>
                      <input type=radio name=xest value=A checked>Activo
                      <input type=radio name=xest value=X>Inactivo
                  </c:otherwise>
                </c:choose>
            </td></tr>
            </tbody>
            </table>
            <input type=submit name=boton class="btn btn-primary" value="GRABAR">
            <input type=submit name=boton class="btn btn-dark" value="CANCELAR">
        </form>
    </div>
    </body>
</html>

