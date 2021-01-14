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
            <input type=HIDDEN name=accion value="NuevoEliminarAlumno">
            <input type=submit name=boton class="btn btn-primary" value="Nuevo Registro">
            <input type=submit name=boton class="btn btn-dark" value="Eliminar Registros">
            <table class="table table-striped table-hover">
            <thead>
            <tr>
            <th scope="col">#</th>
            <th scope="col">Codigo</th>
            <th scope="col">Nombre</th>
            <th scope="col">Direccion</th>
            <th scope="col">Email</th>
            <th scope="col">Telefono</th>
            <th scope="col">Celular</th>
            <th scope="col">Sexo</th>
            <th scope="col">Fec-Nac</th>
            <th scope="col">Estado</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${arrAlumnos}" var="alumno">
            <tr>
            <td><input type=checkbox name='xcod'
                  value=<c:out value='${alumno.codigo}'/> > </td>
            <td><c:out value='${alumno.codigo}'/></td>
            <td><a href="/WebAppSistema/controladorPrincipal?accion=modificarAlumno&xcod=
                     <c:out value='${alumno.codigo}'/>">
                <c:out value='${alumno.nombre}' /></a></td>
            <td><c:out value='${alumno.direccion}'/></td>
            <td><c:out value='${alumno.email}'/></td>
            <td><c:out value='${alumno.telefono}'/></td>
            <td><c:out value='${alumno.celular}'/></td>
            <td><c:out value='${alumno.sexo}'/></td>
            <td><c:out value='${alumno.fec_nac}'/></td>
            <td><c:out value='${alumno.estado}'/></td>
            </tr>
            </c:forEach>
            </tbody>
            </table>
        </form>
    </div>
    </body>
</html>
