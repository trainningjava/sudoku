package br.com.dio.ui.custom.input;

import br.com.dio.model.Space;
import br.com.dio.service.BoardService;
import br.com.dio.service.EventEnum;
import br.com.dio.service.EventListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Dimension;
import java.awt.Font;

import static br.com.dio.service.EventEnum.CLEAR_SPACE;
import static java.awt.Font.PLAIN;

public class NumberText extends JTextField implements EventListener {

    private final Space space;
    private final BoardService boardService;

    public NumberText(final Space space, final BoardService boardService) {
        this.space = space;
        this.boardService = boardService;
        var dimension = new Dimension(50, 50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!space.isFixed());
        if (space.isFixed()){
            this.setText(space.getActual().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(final DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                changeSpace();
            }

            private void changeSpace() {
                if (getText().isEmpty()) {
                    space.clearSpace();
                    return;
                }
                Integer oldValue = space.getActual();

                space.setActual(Integer.parseInt(getText()));

                if (!boardService.isBoardValid()) {

                    space.setActual(oldValue);

                    javax.swing.SwingUtilities.invokeLater(() -> {
                        setText(oldValue == null ? "" : oldValue.toString());

                        javax.swing.JOptionPane.showMessageDialog(
                                null,
                                "Movimento inválido pelas regras do Sudoku."
                        );
                    });
                }
            }
        });
    }

    @Override
    public void update(final EventEnum eventType) {
        if (eventType.equals(CLEAR_SPACE) && (this.isEnabled())){
            this.setText("");
        }
    }
}
