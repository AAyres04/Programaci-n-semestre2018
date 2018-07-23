<!DOCTYPE html>
<html>
<body>

<?php include("class_lib.php");?>
<?php

class TestSoftware{
		function __construct(){
				$soft = new Software();
				$soft->set_licencia('holi');
				$soft->mostrar();
		}
}

class Desarrollador {
	
		protected $nombre;
	
		function __construct($name){
				$this->nombre = $name;
		}
	
		function get_nombre() {
				return $this->nombre;
		}
		
		function set_nombre($name){
				$this->nombre = $name;
		}
		
}
		
abstract class Producto{
		protected $codigo;
		protected $nombre;
		
		function get_codigo(){
				return $this->codigo;
		}
		
		function set_codigo($code){
				$this->codigo = $code;
		}
		
		function get_nombre(){
				return $this->nombre;
		}
		
		function set_nombre($name){
				$this->nombre = $name;
		}
}

interface Interface{
		public function mostrar()
}

class Software extends Producto implements Interface{
		protected $licencia;
		protected $version;
		
		function __construct(){
				$this->codigo = 0;
				$this->nombre = '';
				$this->licencia = '';
				$this->version = '';
		}
		
		function __construct($code, $name, $license, $ver){
				$this->codigo = $code;
				$this->nombre = $name;
				$this->licencia = $license;
				$this->version = $ver;
		}

		public function get_licencia(){
				return $this->licencia;
		}
		
		public function set_licencia($license){
				$this->licencia = $license;
		}
		
		public function get_version(){
				return $this->version;
		}
		
		public function set_version($ver){
				return $this->version = $ver;
		}
		
		public function mostrar(){
				echo 'Nombre: ' + $nombre + '\n';
				echo 'Codigo: ' + $codigo + '\n';
				echo 'Licencia: ' + $licencia + '\n';
				echo 'Version: ' + $version;
		}
		



}



?>

</body>
</html>