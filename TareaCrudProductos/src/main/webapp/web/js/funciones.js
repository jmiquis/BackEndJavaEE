/**
 * Funciones auxiliares de javascripts 
 */
function confirmarBorrar(nombre,id){
  if (confirm("¿Quieres eliminar el usuario:  "+nombre+" con el id "+id+" ?"))
  {
   document.location.href="?orden=Borrar&producto_no="+id;
  }
}