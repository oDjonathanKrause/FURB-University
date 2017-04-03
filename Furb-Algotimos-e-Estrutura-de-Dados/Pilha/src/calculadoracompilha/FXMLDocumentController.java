package calculadoracompilha;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pilha.PilhaVetor;

/**
 *
 * @author djonathan.krause
 */
public class FXMLDocumentController implements Initializable
{

    private PilhaVetor<String> pilhaVetor = new PilhaVetor<>();

    @FXML
    private Label lblValorTopo;
    @FXML
    private Label lblRetorno;
    @FXML
    private Label lblTamanho;
    @FXML
    private TextField fldValor;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnVerificarTamanho;

    @FXML
    private void inserirAction(ActionEvent event)
    {
        try
        {
            if (fldValor.getText().isEmpty())
            {
                lblRetorno.setText("Valor não pode ser nulo");
                System.out.println("Valor não pode ser nulo");
            } else
            {
                // Insere o numero no topo da pilha
                pilhaVetor.push(fldValor.getText());

                // Limpa field
                fldValor.setText("");

                // Mostra o valor inserido na console
                System.out.println("Inserido " + pilhaVetor.peek());
                lblRetorno.setText("Inserido " + pilhaVetor.peek());
            }

        } catch (Exception ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void pegaTopoAction(ActionEvent event)
    {
        if (pilhaVetor.vazia())
        {
            lblRetorno.setText("Pilha vazia");
            System.out.println("Pilha vazia");

        } else
        {
            lblValorTopo.setText(pilhaVetor.peek());
        }
    }

    @FXML
    private void removerAction(ActionEvent event) throws Exception
    {
        if (pilhaVetor.vazia())
        {
            lblRetorno.setText("Pilha vazia");
            System.out.println("Pilha vazia");

        } else
        {
            lblRetorno.setText("Valor removido: " + pilhaVetor.getTopo());
            pilhaVetor.pop();

        }
    }

    @FXML
    private void verificarTamanhoAction(ActionEvent event) throws Exception
    {
        if (pilhaVetor.vazia())
        {
            lblRetorno.setText("Pilha vazia");
            System.out.println("Pilha vazia");

        } else
        {
            lblTamanho.setText("Tamanho da pilha: " + pilhaVetor.getQtdElementos());
        }
    }
    
    @FXML
    private void limpaFields()
    {
        lblRetorno.setText("");
        lblTamanho.setText("");
        lblValorTopo.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
