package Interface;

import Controle.Verificador.AnalisadorLexico;
import Controle.Verificador.Compilador;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Caret;
import Controle.lexico.LexicalError;
import Controle.lexico.Lexico;
import Controle.semantico.SemanticError;
import Controle.semantico.Semantico;
import Controle.sintatico.Sintatico;
import Controle.sintatico.SyntaticError;
import java.io.IOException;

/**
 * @author Tamires, Djonathan
 */
public class Apresentacao extends javax.swing.JFrame
{

    private String copiarStr;
    private Path caminhoArquivo;
    private Semantico semantico;

    public Apresentacao()
    {
        initComponents();
        configuraEditor();
        configuraAreaMensagens();
        barraStatus.setText("Não modificado");
        inicializaTeclasAtalho();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraFerramentas = new java.awt.Panel();
        novo = new javax.swing.JButton();
        abrir = new javax.swing.JButton();
        salvar = new javax.swing.JButton();
        copiar = new javax.swing.JButton();
        colar = new javax.swing.JButton();
        recortar = new javax.swing.JButton();
        compilar = new javax.swing.JButton();
        gerarCod = new javax.swing.JButton();
        equipe = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        editor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaMensagens = new javax.swing.JTextArea();
        painelStatus = new javax.swing.JPanel();
        barraStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(915, 610));
        setSize(new java.awt.Dimension(900, 610));

        barraFerramentas.setBackground(new java.awt.Color(240, 240, 240));
        barraFerramentas.setMinimumSize(new java.awt.Dimension(144, 544));
        barraFerramentas.setPreferredSize(new java.awt.Dimension(144, 544));
        barraFerramentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/imagens/filenew.png"))); // NOI18N
        novo.setText("novo [crtl -n]");
        novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoActionPerformed(evt);
            }
        });
        barraFerramentas.add(novo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 144, 62));

        abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/imagens/i_abrir.png"))); // NOI18N
        abrir.setText("abrir [crtl-a]");
        abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirActionPerformed(evt);
            }
        });
        barraFerramentas.add(abrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 144, 62));

        salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/imagens/i_save.png"))); // NOI18N
        salvar.setText("salvar [crtl-s]");
        salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarActionPerformed(evt);
            }
        });
        barraFerramentas.add(salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 144, 62));

        copiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/imagens/i_copiar.png"))); // NOI18N
        copiar.setText("copiar [crtl-c]");
        copiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copiarActionPerformed(evt);
            }
        });
        barraFerramentas.add(copiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 144, 62));

        colar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/imagens/i_colar.png"))); // NOI18N
        colar.setText("colar [ctrl-v]");
        colar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colarActionPerformed(evt);
            }
        });
        barraFerramentas.add(colar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 144, 62));

        recortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/imagens/i_recortar.png"))); // NOI18N
        recortar.setText("recortar [ctrl-x]");
        recortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recortarActionPerformed(evt);
            }
        });
        barraFerramentas.add(recortar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 144, 62));

        compilar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/imagens/i_compilar.png"))); // NOI18N
        compilar.setText("compilar [F8]");
        compilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compilarActionPerformed(evt);
            }
        });
        barraFerramentas.add(compilar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 144, 62));

        gerarCod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/imagens/i_gerarCod.png"))); // NOI18N
        gerarCod.setText("gerar código [F9]");
        gerarCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerarCodActionPerformed(evt);
            }
        });
        barraFerramentas.add(gerarCod, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 144, 62));

        equipe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface/imagens/i_equipe.png"))); // NOI18N
        equipe.setText("equipe [F1]");
        equipe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipeActionPerformed(evt);
            }
        });
        barraFerramentas.add(equipe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 144, 62));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setFocusCycleRoot(true);
        jScrollPane1.setInheritsPopupMenu(true);

        editor.setTabSize(3);
        editor.setMinimumSize(new java.awt.Dimension(750, 450));
        editor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editorKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(editor);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setAutoscrolls(true);

        areaMensagens.setEditable(false);
        areaMensagens.setMaximumSize(new java.awt.Dimension(750, 110));
        areaMensagens.setMinimumSize(new java.awt.Dimension(750, 110));
        areaMensagens.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(areaMensagens);

        painelStatus.setMinimumSize(new java.awt.Dimension(900, 25));
        painelStatus.setPreferredSize(new java.awt.Dimension(900, 25));

        javax.swing.GroupLayout painelStatusLayout = new javax.swing.GroupLayout(painelStatus);
        painelStatus.setLayout(painelStatusLayout);
        painelStatusLayout.setHorizontalGroup(
            painelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barraStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(697, Short.MAX_VALUE))
        );
        painelStatusLayout.setVerticalGroup(
            painelStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelStatusLayout.createSequentialGroup()
                .addComponent(barraStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(barraFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(4, 4, 4))
            .addComponent(painelStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
                .addComponent(painelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void novoActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_novoActionPerformed
    {//GEN-HEADEREND:event_novoActionPerformed
        novo();
    }//GEN-LAST:event_novoActionPerformed

    private void editorKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_editorKeyTyped
    {//GEN-HEADEREND:event_editorKeyTyped
        barraStatus.setText("Modificado");
    }//GEN-LAST:event_editorKeyTyped

    private void abrirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_abrirActionPerformed
    {//GEN-HEADEREND:event_abrirActionPerformed
        abrir();
    }//GEN-LAST:event_abrirActionPerformed

    private void salvarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_salvarActionPerformed
    {//GEN-HEADEREND:event_salvarActionPerformed
        salvar();
    }//GEN-LAST:event_salvarActionPerformed

    private void copiarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_copiarActionPerformed
    {//GEN-HEADEREND:event_copiarActionPerformed
        copiar();
    }//GEN-LAST:event_copiarActionPerformed

    private void colarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_colarActionPerformed
    {//GEN-HEADEREND:event_colarActionPerformed
        colar();
    }//GEN-LAST:event_colarActionPerformed

    private void recortarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_recortarActionPerformed
    {//GEN-HEADEREND:event_recortarActionPerformed
        recortar();
    }//GEN-LAST:event_recortarActionPerformed

    private void compilarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_compilarActionPerformed
    {//GEN-HEADEREND:event_compilarActionPerformed
        compilar();
    }//GEN-LAST:event_compilarActionPerformed

    private void gerarCodActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_gerarCodActionPerformed
    {//GEN-HEADEREND:event_gerarCodActionPerformed
        gerarCod();
    }//GEN-LAST:event_gerarCodActionPerformed

    private void equipeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_equipeActionPerformed
    {//GEN-HEADEREND:event_equipeActionPerformed
        equipe();
    }//GEN-LAST:event_equipeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Windows".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Apresentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Apresentacao().setVisible(true);
            }
        });
    }

    private void configuraEditor()
    {
        NumberedBorder pBorda = new NumberedBorder();
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        editor.setBorder(pBorda);
    }

    private void configuraAreaMensagens()
    {
        NumberedBorder pBorda = new NumberedBorder();
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        editor.setBorder(pBorda);

    }

    private void btnPrecionado()
    {
        editor.setText(null);
        caminhoArquivo = null;
        this.setTitle("");
        naoModificado();
    }

    public void novo()
    {
        System.out.println("Novo arquivo");
        btnPrecionado();
    }

    public void abrir()
    {

        JFileChooser fc = new JFileChooser();

        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("Documentos de texto (*.txt)", "txt");
        fc.addChoosableFileFilter(txtFilter);
        fc.setFileFilter(txtFilter);

        fc.showOpenDialog(null);

        if (fc.getSelectedFile() != null)
        {
            novo();
            caminhoArquivo = fc.getSelectedFile().toPath();
            editor.setText(getConteudoArquivo(caminhoArquivo));
//            novoDocumento = false;
//            salvo = true;
            this.setTitle(caminhoArquivo.toString() + " - Bloco de notas");
        }

    }

    private String getConteudoArquivo(Path path)
    {
        String linha, conteudo = "";
        try
        {
            BufferedReader bf = new BufferedReader(new FileReader(path.toString()));
            while ((linha = bf.readLine()) != null)
            {
                conteudo += linha + "\n";
            }
            bf.close();
        } catch (Exception e)
        {
            System.err.println("Erro ao abrir o arquivo " + path + "\n" + e.getMessage());
        }
        return conteudo;
    }

    private void salvar()
    {
        System.out.println("Salvando");
        BufferedWriter bf;
        String conteudo = editor.getText();

        try
        {

            if (caminhoArquivo == null
                    || !Files.exists(caminhoArquivo))
            {
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.showSaveDialog(this);
                caminhoArquivo = Paths.get(fc.getSelectedFile().toString() + ".txt");
                Files.createFile(caminhoArquivo);
                this.setTitle(caminhoArquivo.toString());
            }

            bf = new BufferedWriter(new FileWriter(caminhoArquivo.toString()));
            bf.write(conteudo);
            bf.flush();
            bf.close();
            naoModificado();

        } catch (Exception e)
        {
            System.err.println("Erro ao salvar o arquivo " + caminhoArquivo.toString() + "\n" + e.getMessage());
        }

    }

    private void naoModificado()
    {
        barraStatus.setText("Não modificado");
        areaMensagens.setText(null);
    }

    private void copiar()
    {
        copiarStr = editor.getSelectedText();
    }

    private void colar()
    {
        editor.insert(copiarStr, editor.getCaretPosition());
    }

    private void recortar()
    {
        System.out.println("recortar");
        copiarStr = editor.getSelectedText();
        Caret caret = editor.getCaret();
        int p0 = Math.min(caret.getDot(), caret.getMark());
        int p1 = Math.max(caret.getDot(), caret.getMark());
        editor.replaceRange("", p0, p1);
    }
    
    private void criarArquivoIlasm(String codigoGerado) {
        try {
        salvar();
        String newCaminho = caminhoArquivo.toString().substring(0, caminhoArquivo.toString().lastIndexOf(".")) + ".il";
        Path caminhoIlasm = Paths.get(newCaminho);

        if (!Files.exists(caminhoIlasm)) {
            Files.createFile(caminhoIlasm);
        }
        BufferedWriter bf = new BufferedWriter(new FileWriter(newCaminho));
        bf.write(codigoGerado);
        bf.flush();
        bf.close();
        } catch (Exception e) {
            System.err.println("Erro gerar arquivo ilasm " + e.getMessage());
        }
    }

    private void compilar() {
        
        if (caminhoArquivo == null
                || "".equals(caminhoArquivo)) {
            salvar();
        }
        
        Compilador comp = new Compilador();
        Lexico lexico = new Lexico();
        Sintatico sintatico = new Sintatico();
        semantico = new Semantico();
        try {
            areaMensagens.setText("");

            String conteudo = editor.getText();
            lexico.setInput(conteudo);
            sintatico.parse(lexico, semantico);
            areaMensagens.append("Programa compilado com sucesso");
        } catch (LexicalError e) {
            areaMensagens.append(comp.setErro(e.getMessage(), e.getLinha(), "LE", sintatico));
        } catch (SyntaticError e) {
            areaMensagens.append(comp.setErro(e.getMessage(), e.getLinha(), "SE", sintatico));
        } catch (SemanticError e) {
            areaMensagens.append(comp.setErro(e.getMessage(), e.getLinha(), "SME", sintatico));
        }
    }

    private void gerarCod()
    {
        if (caminhoArquivo == null
                || "".equals(caminhoArquivo)) {
            salvar();
        }
        
        Compilador comp = new Compilador();
        Lexico lexico = new Lexico();
        Sintatico sintatico = new Sintatico();
        semantico = new Semantico();
        try {
            areaMensagens.setText("");

            String conteudo = editor.getText();
            lexico.setInput(conteudo);
            sintatico.parse(lexico, semantico);
        } catch (LexicalError e) {
            areaMensagens.append(comp.setErro(e.getMessage(), e.getLinha(), "LE", sintatico));
        } catch (SyntaticError e) {
            areaMensagens.append(comp.setErro(e.getMessage(), e.getLinha(), "SE", sintatico));
        } catch (SemanticError e) {
            areaMensagens.append(comp.setErro(e.getMessage(), e.getLinha(), "SME", sintatico));
        }
        criarArquivoIlasm(semantico.getCodigoObjeto());
        areaMensagens.append("código objeto gerado com sucesso");
    }

    private void equipe()
    {
        System.out.println("equipe pelo botão");
        areaMensagens.setText("Djonathan Krause, Tamires Kistner");
    }

    private void inicializaTeclasAtalho()
    {
        //ctrl + n
        novo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "novo");
        novo.getActionMap().put("novo", new AbstractAction()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("abriu pelo atalho");
                novo();
            }

        });

        //ctrl + a
        abrir.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK), "abrir");
        abrir.getActionMap().put("abrir", new AbstractAction()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("abriu pelo atalho");
                abrir();
            }
        });

        //ctrl + s
        salvar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "salvar");
        salvar.getActionMap().put("salvar", new AbstractAction()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.out.println("salvou pelo atalho");
                salvar();
            }
        });

        //ctrl + c
        copiar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "copiar");
        copiar.getActionMap().put("copiar", new AbstractAction()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                copiar();
            }
        });

        //ctrl + v
        colar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), "colar");
        colar.getActionMap().put("colar", new AbstractAction()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                colar();
            }
        });

        //crtl + x
        recortar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK), "recortar");
        recortar.getActionMap().put("recortar", new AbstractAction()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                recortar();
            }
        });

        //F8
        compilar.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), "compilar");
        compilar.getActionMap().put("compilar", new AbstractAction()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                compilar();
            }
        });

        //F9
        gerarCod.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), "gerarCod");
        gerarCod.getActionMap().put("gerarCod", new AbstractAction()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                gerarCod();
            }
        });

        //F1
        equipe.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "equipe");
        equipe.getActionMap().put("equipe", new AbstractAction()
        {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                equipe();
            }
        });
    }
    
    private void configuraJTabe(JTable jTable) {
        Object colunas[] = {"Linha", "Resultado", "Sequencia", "Reconhecimento"};

        DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(null, colunas) {
            //define que a tabela não pode ser editada
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };
        modelo.setNumRows(10);
        jTable.setGridColor(Color.GRAY);
        jTable.setShowHorizontalLines(true);
        jTable.setShowVerticalLines(true);
        jTable.setModel(modelo);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(20);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(300);
    }
    
    public void setSaida(int linha, String classe, String lexema) {
        areaMensagens.append("\n");
        areaMensagens.append(linha + " \t");
        areaMensagens.append(classe);
        areaMensagens.append(getTabLexema(classe));
        areaMensagens.append(lexema);
    }
    
    private String getTabLexema(String classe) {
        String tab = "";
        int lengtClassToLex = ("classe \t \t \t \t \t \t lexema").length();
        int totalTab = lengtClassToLex - classe.length();
        for (int i = 0; i < totalTab; i++) {
            tab += " ";
        }
        return tab;
    }
    
    public void setErro(String erro) {
        areaMensagens.setText(erro);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrir;
    private javax.swing.JTextArea areaMensagens;
    private java.awt.Panel barraFerramentas;
    private javax.swing.JLabel barraStatus;
    private javax.swing.JButton colar;
    private javax.swing.JButton compilar;
    private javax.swing.JButton copiar;
    private javax.swing.JTextArea editor;
    private javax.swing.JButton equipe;
    private javax.swing.JButton gerarCod;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton novo;
    private javax.swing.JPanel painelStatus;
    private javax.swing.JButton recortar;
    private javax.swing.JButton salvar;
    // End of variables declaration//GEN-END:variables

}
