/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package evento;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author xerve
 */
public class RegistroVisitante extends javax.swing.JFrame {
    ArrayList<Visitante> listaVisitantes = new ArrayList<Visitante>();
    ArrayList<Visitante> listaProfesores = new ArrayList<Visitante>();
    ArrayList<Visitante> listaEstudiantes = new ArrayList<Visitante>();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistroVisitante.class.getName());
    
    public RegistroVisitante() {
        initComponents();
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoGROUP = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        limpiarBTN = new javax.swing.JButton();
        cancelarBTN = new javax.swing.JButton();
        confirmarBTN = new javax.swing.JButton();
        nombreTF = new javax.swing.JTextField();
        horaTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        profesorBTN = new javax.swing.JRadioButton();
        estudianteBTN = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        provinciaCBOX = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(6000, 0));

        jLabel1.setText("BIENVENIDO AL REGISTRO PARA EL EVENTO DE LA UFV");
        jLabel1.setPreferredSize(new java.awt.Dimension(363, 30));

        jLabel2.setText("¿Como te llamas? (Nombre completo)");

        jLabel3.setText("¿A que hora vas a llegar?");

        jLabel4.setText("Provincia de procedencia");

        limpiarBTN.setText("Limpiar campos");
        limpiarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarBTNActionPerformed(evt);
            }
        });

        cancelarBTN.setText("Cancelar");
        cancelarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBTNActionPerformed(evt);
            }
        });

        confirmarBTN.setText("Confirmar asistencia");
        confirmarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarBTNActionPerformed(evt);
            }
        });

        nombreTF.setMaximumSize(new java.awt.Dimension(100, 27));
        nombreTF.setMinimumSize(new java.awt.Dimension(100, 27));
        nombreTF.setPreferredSize(new java.awt.Dimension(100, 27));

        horaTF.setMaximumSize(new java.awt.Dimension(130, 27));
        horaTF.setMinimumSize(new java.awt.Dimension(100, 27));
        horaTF.setPreferredSize(new java.awt.Dimension(100, 27));

        jLabel5.setText("Eres: ");

        tipoGROUP.add(profesorBTN);
        profesorBTN.setText("Profesor");
        profesorBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profesorBTNActionPerformed(evt);
            }
        });

        tipoGROUP.add(estudianteBTN);
        estudianteBTN.setText("Estudiante");

        jSeparator1.setMaximumSize(new java.awt.Dimension(500, 10));
        jSeparator1.setMinimumSize(new java.awt.Dimension(500, 10));
        jSeparator1.setPreferredSize(new java.awt.Dimension(500, 10));

        provinciaCBOX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Extranjero  ", "Álava  ", "Albacete  ", "Alicante  ", "Almería  ", "Asturias  ", "Ávila  ", "Badajoz  ", "Islas Baleares  ", "Barcelona  ", "Burgos  ", "Cáceres  ", "Cádiz  ", "Cantabria  ", "Castellón  ", "Ciudad Real  ", "Córdoba  ", "La Coruña  ", "Cuenca  ", "Gerona  ", "Granada  ", "Guadalajara  ", "Guipúzcoa  ", "Huelva  ", "Huesca  ", "Jaén  ", "León  ", "Lérida  ", "Lugo  ", "Madrid  ", "Málaga  ", "Murcia  ", "Navarra  ", "Orense  ", "Palencia  ", "Las Palmas  ", "Pontevedra  ", "La Rioja  ", "Salamanca  ", "Santa Cruz de Tenerife  ", "Segovia  ", "Sevilla  ", "Soria  ", "Tarragona  ", "Teruel  ", "Toledo  ", "Valencia  ", "Valladolid  ", "Vizcaya  ", "Zamora  ", "Zaragoza" }));
        provinciaCBOX.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(28, 28, 28)
                                        .addComponent(profesorBTN))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(estudianteBTN)
                                    .addComponent(horaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(provinciaCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(limpiarBTN)
                                .addGap(18, 18, 18)
                                .addComponent(cancelarBTN)
                                .addGap(18, 18, 18)
                                .addComponent(confirmarBTN)))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombreTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(horaTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(provinciaCBOX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(estudianteBTN)
                    .addComponent(profesorBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limpiarBTN)
                    .addComponent(cancelarBTN)
                    .addComponent(confirmarBTN))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarBTNActionPerformed
        nombreTF.setText("");
        provinciaCBOX.setSelectedIndex(1);
        horaTF.setText("");
        tipoGROUP.clearSelection();
    }//GEN-LAST:event_limpiarBTNActionPerformed

    private void cancelarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBTNActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
            null,
            "¿Seguro que quieres salir de esta ventana? \n"
                    + "Tus datos no se guardarán",
            "Confirmar salida",
            JOptionPane.CANCEL_OPTION);
        if(confirm != 2){
            System.exit(0);
        }
    }//GEN-LAST:event_cancelarBTNActionPerformed

    private void confirmarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarBTNActionPerformed
        if (nombreTF.getText().isEmpty() ||
            horaTF.getText().isEmpty() ||
            tipoGROUP.getSelection() == null) {

            JOptionPane.showMessageDialog(
                null,
                "Debes Introducir todos los datos",
                "Error en los campos",
                JOptionPane.ERROR_MESSAGE
            );

        } else if (!horaTF.getText().matches("\\d{2}:\\d{2}")) {

            JOptionPane.showMessageDialog(
                null,
                "Debes Introducir la hora en formato HH:MM",
                "Error en los campos",
                JOptionPane.ERROR_MESSAGE
            );

        } else {
            Visitante visitante = new Visitante(
                nombreTF.getText(),
                provinciaCBOX.getSelectedItem().toString(),
                horaTF.getText(),
                profesorBTN.isSelected()
            );

            listaVisitantes.add(visitante);
            procesarVisitante(visitante);
        }     
    }//GEN-LAST:event_confirmarBTNActionPerformed

    private void procesarVisitante(Visitante visitante) {
        if("norte".equals(visitante.getAla())){
            JOptionPane.showMessageDialog(
            null,
            "Estás en el ala norte",
            "Información",
            JOptionPane.INFORMATION_MESSAGE
        );
        }else{
            JOptionPane.showMessageDialog(
            null,
            "Estás en el ala sur",
            "Información",
            JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
    
    
    
    
    private void profesorBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profesorBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profesorBTNActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new RegistroVisitante().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarBTN;
    private javax.swing.JButton confirmarBTN;
    private javax.swing.JRadioButton estudianteBTN;
    private javax.swing.JTextField horaTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton limpiarBTN;
    private javax.swing.JTextField nombreTF;
    private javax.swing.JRadioButton profesorBTN;
    private javax.swing.JComboBox<String> provinciaCBOX;
    private javax.swing.ButtonGroup tipoGROUP;
    // End of variables declaration//GEN-END:variables

    
}
