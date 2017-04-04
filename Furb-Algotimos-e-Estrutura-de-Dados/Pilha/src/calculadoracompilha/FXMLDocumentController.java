package calculadoracompilha;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    private TextField fldExpressao;
    @FXML
    private Label lblResultadoExpressao;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnVerificarTamanho;
    @FXML
    private ListView pilha;

    @FXML
    private void inserirAction(ActionEvent event)
    {
        try
        {
            // Pega valor da field
            String valorField = fldValor.getText();

            if (fldValor.getText().isEmpty())
            {
                lblRetorno.setText("Valor não pode ser nulo");
                System.out.println("Valor não pode ser nulo");
            } else
            {
                // Insere o numero no topo da pilha
                pilhaVetor.push(valorField);

                // Limpa field
                fldValor.setText("");

                // Mostra o valor inserido na console
                System.out.println("Inserido " + pilhaVetor.peek());
                lblRetorno.setText("Inserido " + pilhaVetor.peek());

                // Atualiza lista
                pilha.getItems().add(0, valorField);

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
            lblRetorno.setText("Valor removido: " + pilha.getItems().get(0));
            pilhaVetor.pop();

            pilha.getItems().remove(0);

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
    private void calcularExpressaoAction(ActionEvent event) throws Exception
    {
        CalculadoraComPilha calculadora = new CalculadoraComPilha();
        int resultadoExpressao = calculadora.calcula(fldExpressao.getText());
        
        lblResultadoExpressao.setText("Resultado da Expressão: " + resultadoExpressao);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
