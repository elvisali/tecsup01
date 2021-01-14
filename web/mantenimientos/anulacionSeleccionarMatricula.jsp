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
        <h4 class="display-8">Seleccionar Matricula a Anular</h4>
        <form method=POST action="/WebAppSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="anulacionMostrarMatricula">
            <table class="table table-striped table-hover">
            <tbody>
            <tr><td>
            Ingrese Nro Documento: <input name="xndoc" size=30>
            </td></tr>
            <tr><td>
            <input type=submit name=boton class="btn btn-primary" value="Anular Matricula">
            <input type=submit name=boton class="btn btn-dark" value="Cancelar">
            </td></tr>
            </tbody>
            </table>
        </form>
    </div>
    </body>
</html>
