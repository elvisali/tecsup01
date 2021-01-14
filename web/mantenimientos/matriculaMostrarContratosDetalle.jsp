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
        <form method=POST action="/WebAppSistema/controladorPrincipal">
            <input type=HIDDEN name=accion value="matriculaMostrarContratoCursos">
            <input type="hidden" name="xndoc" value="<c:out value='${xndoc}'/>">
            <h4 class="display-8">Datos de Matricula</h4>
            <table class="table table-striped table-hover">
                <tr>
                <td>Nro doc.:</td>  <td><c:out value='${xndoc}'/> </td>
                </tr>
                <tr>
                <td>Alumno:</td>    <td><c:out value='${arrMatr[0]}'/> </td>
                </tr>
                <tr>
                <td>Fecha:</td>     <td><c:out value='${arrMatr[1]}'/> </td>
                </tr>
                <tr>
                <td>Total:</td>     <td><c:out value='${arrMatr[2]}'/> </td>
                </tr>
                <tr>
                <td>Estado:</td>    <td><c:out value='${arrMatr[3]}'/> </td>
                </tr>
            </table>
                <hr>
                <h4 class="display-8">  Cursos  <a target="derecha" href="controladorPrincipal?accion=matriculaMostrarContratoCursos&boton=Lista&xndoc=<c:out value='${xndoc}'/>" class="btn btn-info">Agregar Curso</a> </h4> 
            
            <table class="table table-striped table-hover">
                <tr>
                    <th>Curso</th>     
                    <th>Monto</th>     
                    <th>Op</th>
                </tr>
                <c:forEach items="${arrDetM}" var="registro">
                    <tr>
                        <td><c:out value='${registro[0]}'/></td>
                        <td><c:out value='${registro[1]}'/></td>
                        <td><c:out value='${registro[2]}'/></td>
                        <td> <a href="controladorPrincipal?accion=matriculaMostrarContratoCursos&boton=EliminarCurso&xndoc=<c:out value='${xndoc}'/>&xcodCurso=<c:out value='${registro[0]}'/>" class="btn btn-danger">Eliminar</a>  </td>
                    </tr>
                </c:forEach> 
                <tr> 
                    <td colspan="2" align="right"> 
                        <input type="submit" name="boton" class="btn btn-dark" value="Retornar">
                    </td> 
                </tr>
            </table>
        </form>
    </div>
    </body>
</html>
