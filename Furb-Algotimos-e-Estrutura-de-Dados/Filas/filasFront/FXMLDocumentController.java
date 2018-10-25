package filasFront;

import filas.FilaVetor;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author djonathan.krause
 */
public class FXMLDocumentController implements Initializable
{

    FilaVetor<Integer> filaVetor = new FilaVetor<>();

    @FXML
    private TextField fldValor;

    @FXML
    private void insereValor(ActionEvent event)
    {
        if (filaVetor.cheia())
        {
            System.out.println("Fila cheia!");
        } else
        {
            String valorField = fldValor.getText();

            filaVetor.insere(valorField);

            System.out.println("Valor inserido: " + valorField);
        }
    }

    @FXML
    private void liberaFila(ActionEvent event)
    {
        filaVetor.libera();
        System.out.println("Fila liberada!");
    }

    @FXML
    private void retiraValor(ActionEvent event)
    {
        System.out.println("Valor retirado: " + filaVetor.retira());
    }

    @FXML
    private void verificaVazia(ActionEvent event)
    {
        System.out.println("Fila vazia? " + filaVetor.vazia());
    }

    @FXML
    private void verificaCheia(ActionEvent event)
    {
        System.out.println("Fila cheia? " + filaVetor.cheia());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
