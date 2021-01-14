<%-- 
    Document   : listadoAlumnos
    Created on : 25-nov-2020, 12:47:02
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
    <div class="card" style="padding: 30px 0px 0px 30px;">
        <h4 class="display-8">Listado de Alumnos</h4>
        <form method=POST action="/WebAppSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="NuevoEliminarCurso">
            <input type=submit name=boton class="btn btn-primary" value="Nuevo Registro">
            <input type=submit name=boton class="btn btn-dark" value="Eliminar Registros">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
            <th scope="col">#</th>
            <th scope="col">codigo </th>
            <th scope="col">nombre </th>
            <th scope="col">costo </th>
            <th scope="col">fec_ini </th>
            <th scope="col">fec_fin </th>
            <th scope="col">duracion </th>
            <th scope="col">sesiones </th>
            <th scope="col">capacidad </th>
            <th scope="col">inscritos </th>
            <th scope="col">estado </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${arrCursos}" var="curso">
            <tr>
            <td><input type=checkbox name='xcod' value=<c:out value='${curso.codigo}'/> > </td>
          
            <td><c:out value='${curso.codigo}'/></td>
<td><a href="/WebAppSistema/controladorPrincipal?accion=modificarCurso&xcod=<c:out value='${curso.codigo}'/>"><c:out value='${curso.nombre}' /></a>
    <c:out value='${curso.nombre}'/></td>
<td><c:out value='${curso.costo}'/></td>
<td><c:out value='${curso.fec_ini}'/></td>
<td><c:out value='${curso.fec_fin}'/></td>
<td><c:out value='${curso.duracion}'/></td>
<td><c:out value='${curso.sesiones}'/></td>
<td><c:out value='${curso.capacidad}'/></td>
<td><c:out value='${curso.inscritos}'/></td>
<td><c:out value='${curso.estado}'/></td>
            
            </tr>
            </c:forEach>
            </tbody>
            </table>
        </form>
    </div>
    </body>
</html>
