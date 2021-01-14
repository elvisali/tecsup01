<%-- 
    Document   : listadoAreas
    Created on : 29-oct-2020, 18:29:37
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
        <h4 class="display-8">Listado de Areas</h4>
        <form method=POST action="/WebAppSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="NuevoEliminarArea">
            <input type=submit name=boton class="btn btn-primary" value="Nuevo Registro">
            <input type=submit name=boton class="btn btn-dark" value="Eliminar Registros">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
            <th scope="col">#</th>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Abreviatura</th>
            <th scope="col">Estado</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${arrAreas}" var="area">
            <tr>
            <td><input type=checkbox name='xcod'
                  value=<c:out value='${area[0]}'/> > </td>
            <td><c:out value='${area[0]}'/></td>
            <td><a href="/WebAppSistema/controladorPrincipal?accion=modificarArea&xcod=
                     <c:out value='${area[0]}'/>">
                <c:out value='${area[1]}' /></a></td>
            <td><c:out value='${area[2]}'/></td>
            <td><c:out value='${area[3]}'/></td>
            </tr>
            </c:forEach>
            </tbody>
            </table>
        </form>
    </div>
    </body>
</html>

