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
    <div class="card" style="width: 50rem; padding: 30px 0px 0px 30px;">
        <h4 class="display-8">Seleccionar Matricula</h4>
        <form method=POST action="/WebAppSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="matriculaMostrarContrato">
            <input type=HIDDEN name=modo value="busqueda">
            <table class="table table-striped table-hover">
            <tbody>
            <tr><td>
            Buscar Matricula: <input name="xnro" value="<c:out value='${nombre}'/>"
                size=60>
            </td></tr>
            <tr><td>
            <select name="xndoc" size="10" class="form-control" id="exampleFormControlSelect2">
              <c:forEach items="${arrMatriculas}" var="item">
                <option value=<c:out value='${item.nro}'/> >
                  <c:out value='${item.nro}'/> / <c:out value='${item.nombre}'/> /<c:out value='${item.total}'/>
                </option>
              </c:forEach>
            </select>
            </td></tr>
            <tr><td>
            <input type=submit name=boton class="btn btn-primary" value="Buscar">
            <input type=submit name=boton class="btn btn-dark" value="Registrar">
            <input type=submit name=boton class="btn btn-dark" value="Editar">
            </td></tr>
            </tbody>
            </table>
        </form>
    </div>
    </body>
</html>

