/*
 * MainFrame.java
 *
 * Created on 24-mei-2011, 11:32:44
 */
package puzzlesolver.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import puzzlesolver.model.*;
import puzzlesolver.model.Puzzle;
import puzzlesolver.solvers.BacktrackSolver;
import puzzlesolver.solvers.PrintBox;
import puzzlesolver.solvers.Solver;
import puzzlesolver.solvers.SolverListener;
import puzzlesolver.textio.*;

/**
 * Main GUI
 *
 * @author Maikel Steneker (0753708)
 * @since 12-6-2011
 */
public class MainFrame extends javax.swing.JFrame {

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
        changeProperties();
        oneSolutionCheckBox.setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        mainPanel = new javax.swing.JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintMainPanel(this, g);
            }
        };
        solveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        oneSolutionCheckBox = new javax.swing.JCheckBox();
        showPlacementsCheckBox = new javax.swing.JCheckBox();
        logCheckBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTextArea = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openPuzzleMenuItem = new javax.swing.JMenuItem();
        saveOutputMenuItem = new javax.swing.JMenuItem();
        puzzleMenu = new javax.swing.JMenu();
        solvePuzzleItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));
        mainPanel.setAutoscrolls(true);
        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainPanelMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainPanelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mainPanelMouseReleased(evt);
            }
        });
        mainPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mainPanelMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                mainPanelMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1378, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        solveButton.setText("Solve");
        solveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        oneSolutionCheckBox.setText("Stop after one solution");

        showPlacementsCheckBox.setText("Show all placements");

        logCheckBox.setText("Log all solutions in text");
        logCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logCheckBoxActionPerformed(evt);
            }
        });

        mainTextArea.setColumns(20);
        mainTextArea.setEditable(false);
        mainTextArea.setFont(new java.awt.Font("Courier New", 0, 13));
        mainTextArea.setRows(5);
        mainTextArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        mainTextArea.setEnabled(false);
        jScrollPane1.setViewportView(mainTextArea);

        fileMenu.setText("File");

        openPuzzleMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openPuzzleMenuItem.setText("Open Puzzle");
        openPuzzleMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openPuzzleMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openPuzzleMenuItem);

        saveOutputMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveOutputMenuItem.setText("Save Text Output");
        saveOutputMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOutputMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveOutputMenuItem);

        menuBar.add(fileMenu);

        puzzleMenu.setText("Puzzle");

        solvePuzzleItem.setText("Solve");
        solvePuzzleItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solvePuzzleItemActionPerformed(evt);
            }
        });
        puzzleMenu.add(solvePuzzleItem);

        menuBar.add(puzzleMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logCheckBox)
                            .addComponent(showPlacementsCheckBox)
                            .addComponent(oneSolutionCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(solveButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clearButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1179, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(solveButton)
                            .addComponent(cancelButton)
                            .addComponent(clearButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(oneSolutionCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPlacementsCheckBox)
                        .addGap(2, 2, 2)
                        .addComponent(logCheckBox)
                        .addGap(11, 11, 11))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openPuzzleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openPuzzleMenuItemActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Puzzle description files", "txt");
        fc.setFileFilter(filter);
        if (selectedFile != null) {
            fc.setCurrentDirectory(new File(selectedFile, ".."));
        }
        int showOpenDialog = fc.showOpenDialog(this);
        this.selectedFile = fc.getSelectedFile();
        if (showOpenDialog == JFileChooser.APPROVE_OPTION) {
            readFile(selectedFile);
        }
        repaint();
        changeProperties();
    }//GEN-LAST:event_openPuzzleMenuItemActionPerformed

    private void mainPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseClicked
        //check which mouse button was pressed
        switch (evt.getButton()) {
            case MouseEvent.BUTTON1:
                break; //left mouse button
            case MouseEvent.BUTTON2:
                break; //middle mouse button
            case MouseEvent.BUTTON3:
                handleTurning(evt);
                break; //right mouse button
        }
        changeProperties();
    }//GEN-LAST:event_mainPanelMouseClicked

    private void solveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solveButtonActionPerformed
        solve();
        changeProperties();
        mainTextArea.setText("");
    }//GEN-LAST:event_solveButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        if (thread != null) {
            thread.stop();
        }
        clear();
        changeProperties();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void mainPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMousePressed
        if (evt.getButton() == MouseEvent.BUTTON1 && !solving
                && Image.pointsAtPiece(currentPuzzle, evt.getPoint())) {
            if (dragNdrop) {
                offset = Image.determineOffset(currentPuzzle, evt.getPoint());
            }
            handleMovement(evt);
        }
        changeProperties();
    }//GEN-LAST:event_mainPanelMousePressed

    private void mainPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseReleased
        dragPoint = null;
        if (evt.getButton() == MouseEvent.BUTTON1 && currentMove != null) {
            handleMovement(evt);
            currentMove = null;
        }
        changeProperties();
    }//GEN-LAST:event_mainPanelMouseReleased

    private void mainPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseMoved
        //check if the user points at a piece
        if (!solving && Image.pointsAtPiece(currentPuzzle, evt.getPoint())) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
        changeProperties();
    }//GEN-LAST:event_mainPanelMouseMoved

    private void mainPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseDragged
        if (currentMove != null) {
            this.dragPoint = evt.getPoint();
            Position pos = null;
            int index;
            if (Image.pointsToBox(currentPuzzle, evt.getPoint())) {
                pos = Image.findPositionInBox(currentPuzzle, evt.getPoint());
            }

            if (pos != null && currentMove.getDirection() == Direction.place) {
                pos = currentMove.correctPosition(pos);
            }

            if (currentMove.getDirection() == Direction.place) {
                Box vbox = Image.generateVirtualBox(
                        currentPuzzle.getBagOfPieces());
                Piece piece = vbox.getCell(currentMove.getInBag()).
                        getPlacement().getPiece();
                index = currentPuzzle.getBagOfPieces().getIndex(piece);


                if (pos != null && currentPuzzle.canPlace(pos, index)) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                } else {
                    setCursor(Cursor.getDefaultCursor());
                }
            } else if (currentMove.getDirection() == Direction.remove) {
                if (Image.pointsToBag(currentPuzzle, evt.getPoint())) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                } else {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        }
        changeProperties();
        repaint();
    }//GEN-LAST:event_mainPanelMouseDragged

    private void mainPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseExited
        //restore the default cursor
        setCursor(Cursor.getDefaultCursor());
        changeProperties();
    }//GEN-LAST:event_mainPanelMouseExited

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        thread.stop();
        //thread.interrupt();
        changeProperties();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void solvePuzzleItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solvePuzzleItemActionPerformed
        solve();
        changeProperties();
    }//GEN-LAST:event_solvePuzzleItemActionPerformed

    private void logCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logCheckBoxActionPerformed
        mainTextArea.setEnabled(logCheckBox.isSelected());
    }//GEN-LAST:event_logCheckBoxActionPerformed

    private void saveOutputMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOutputMenuItemActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setDialogType(JFileChooser.SAVE_DIALOG);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Puzzle solution output file", "txt");
        fc.setFileFilter(filter);
        if (outputFile != null) {
            fc.setCurrentDirectory(new File(outputFile, ".."));
        }
        int showSaveDialog = fc.showSaveDialog(this);

        outputFile = fc.getSelectedFile();
        if (fc.getFileFilter().equals(filter)) {
            if (outputFile != null && !outputFile.toString().endsWith(".txt")) {
                outputFile = new File(outputFile.toString() + ".txt");
            }
        }
        if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
            try {
                writeOutput();
            } catch (IOException ex) {
                fc.showDialog(this, "There was a problem when writing to the "
                        + "specified file.");
            }
        }
        repaint();
        changeProperties();
    }//GEN-LAST:event_saveOutputMenuItemActionPerformed

    private void readFile(File file) {
        if (file != null) {
            try {
                this.currentPuzzle = ReadPuzzleFromFile.readPuzzleFromFile(file);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(rootPane, "The puzzle description"
                        + " file refers to files that do not exist. "
                        + "Open a valid puzzle description file.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "The puzzle could not "
                        + "be read. Open a valid puzzle description file.");
            }
        }
    }

    private void handleMovement(java.awt.event.MouseEvent evt) {
        //check if the mouse was clicked in the bag or the box
        Position pos;
        if (Image.pointsToBox(currentPuzzle, evt.getPoint())) {
            pos = Image.findPositionInBox(currentPuzzle, evt.getPoint());
        } else if (Image.pointsToBag(currentPuzzle, evt.getPoint())) {
            pos = Image.findPositionInBag(currentPuzzle, evt.getPoint());
        } else {
            pos = null;
        }

        //create a Move object if it doesn't exist yet or the move was cancelled
        if ((currentMove == null || pos == null) && currentPuzzle != null) {
            currentMove = new Move(currentPuzzle);
        }

        //add the given position to the Move object and initiate a move if possible
        if (Image.pointsToBox(currentPuzzle, evt.getPoint())) {
            currentMove.setInBox(pos);
            if (currentMove.getInBag() != null) {
                move();
            }
        } else if (Image.pointsToBag(currentPuzzle, evt.getPoint())) {
            currentMove.setInBag(pos);
            if (currentMove.getInBox() != null) {
                move();
            }
        }
        //check if the user points at a piece
        if (!solving && Image.pointsAtPiece(currentPuzzle, evt.getPoint())) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
        repaint();
    }

    private void handleTurning(java.awt.event.MouseEvent evt) {
        //check if the mouse was clicked in the bag or the box
        if (Image.pointsToBag(currentPuzzle, evt.getPoint())) {
            Position pos = Image.findPositionInBag(currentPuzzle, evt.getPoint());
            Box vbag = Image.generateVirtualBox(currentPuzzle.getBagOfPieces());
            if (vbag.getCell(pos).getPlacement() != null) {
                vbag.getCell(pos).getPlacement().getPiece().turn();
            }
        }
        currentMove = null;
        repaint();
    }

    private void move() {
        if (currentMove.getDirection().equals(Direction.place)) {
            //find out what piece the user selected
            Box vbag = Image.generateVirtualBox(currentPuzzle.getBagOfPieces());
            Piece piece = vbag.getCell(currentMove.getInBag()).
                    getPlacement().getPiece();
            //find the index of this piece in the bag
            int index = currentPuzzle.getBagOfPieces().getIndex(piece);
            //execute the placement
            if (currentPuzzle.canPlace(currentMove.getInBox(), index)) {
                if (printPlacements) {
                    System.out.println("Placing piece at ("
                            + currentMove.getInBag().getRow() + ", "
                            + currentMove.getInBag().getCol() + ") to ("
                            + currentMove.getInBox().getRow() + ", "
                            + currentMove.getInBox().getCol() + ").");
                }
                currentPuzzle.placePiece(currentMove.getInBox(), index);
            }
        } else {
            assert currentMove.getDirection().equals(Direction.remove);
            if (printPlacements) {
                System.out.println("Removing piece at ("
                        + currentMove.getInBox().getRow() + ", "
                        + currentMove.getInBox().getCol() + ").");
            }
            if (currentPuzzle.getBox().getCell(currentMove.getInBox()).
                    getState().equals(CellState.occupied)) {
                currentPuzzle.removePiece(currentMove.getInBox());
            }
        }
        currentMove = null;
        dragPoint = null;
        repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private void paintMainPanel(javax.swing.JPanel p, Graphics g) {
        if (currentPuzzle != null) {
            Image.drawPuzzle(currentPuzzle, g);
        } else {
            g.setColor(Color.RED);
            g.drawString("No puzzle loaded.", 0, 10);
        }
        if (dragNdrop) {
            drawDraggedPiece(dragPoint, offset, g);
        }
        changeProperties();
    }

    private void clear() {
        //read the Puzzle again from the description file
        readFile(selectedFile);
        repaint();
    }

    private void drawDraggedPiece(Point point, Point offset, Graphics g) {
        if (point == null || g == null) {
            return;
        }
        Position pos;
        Piece piece = null;

        if (currentMove != null && currentMove.getDirection() != null) {
            pos = (currentMove.getDirection() == Direction.place) ?
                currentMove.getInBag() : currentMove.getInBox();
            piece = (currentMove.getDirection() == Direction.place) ?
                Image.generateVirtualBox(currentPuzzle.getBagOfPieces()).
                getCell(pos).getPlacement().getPiece() :
                currentPuzzle.getBox().getCell(pos).getPlacement().getPiece();

            Image.drawPiece(piece, g, point, offset);
        }
    }

    private void solve() {
        if (currentPuzzle != null) {
            thread = new Thread() {

                Solver solver;

                @Override
                public void run() {
                    solver = new BacktrackSolver(currentPuzzle);
                    SolutionPainter listener = new SolutionPainter();
                    listener.setWaitForPlacements(
                            showPlacementsCheckBox.isSelected());
                    if (logCheckBox.isSelected()) {
                        solver.addListener(new SolutionLogger());
                    }
                    solver.addListener(listener);
                    solver.findAll();
                    repaint();
                }
            };
            thread.start();
        }
    }

    private void changeProperties() {
        if (thread != null && thread.isAlive()) {
            solving = true;
        } else {
            solving = false;
        }

        this.solveButton.setEnabled(!solving && (currentPuzzle != null));
        this.solvePuzzleItem.setEnabled(!solving && (currentPuzzle != null));
        this.clearButton.setEnabled(currentPuzzle != null);
        this.cancelButton.setEnabled(solving);

        this.setTitle((currentPuzzle == null) ?
            "PuzzleSolver" : currentPuzzle.getName() + " - PuzzleSolver");
    }

    private void writeOutput() throws IOException {
        if (!outputFile.createNewFile()) {
            //the file already exists
            int showConfirmDialog = JOptionPane.showConfirmDialog(this,
                    "The file already exists. Do you want to continue?",
                    "Confirm overwrite", JOptionPane.YES_NO_OPTION);
            if (showConfirmDialog == JOptionPane.NO_OPTION) {
                return;
            }
        }
        FileWriter fstream = new FileWriter(outputFile);
        BufferedWriter out = new BufferedWriter(fstream);

        out.write(mainTextArea.getText());
        out.close();
    }

    private class SolutionPainter implements SolverListener {

        final static private int SOLUTIONWAIT = 300;
        final static private int PLACEMENTWAIT = 100;
        private boolean waitForPlacements = false;

        public void solutionFound(int solutionNumber, Puzzle puzzle) {
            repaint();
            try {
                Thread.sleep(SOLUTIONWAIT);
            } catch (InterruptedException ex) {
                //ignore exception
            }
        }

        public void piecePlaced(Puzzle puzzle) {
            if (waitForPlacements && PLACEMENTWAIT != 0) {
                repaint();

                try {
                    Thread.sleep(PLACEMENTWAIT);
                } catch (InterruptedException ex) {
                    //ignore exception
                }
            }
        }

        public void setWaitForPlacements(boolean wait) {
            this.waitForPlacements = wait;
        }
    }

    private class SolutionLogger implements SolverListener {

        public void solutionFound(int solutionNumber, Puzzle puzzle) {
            if (solutionNumber == 1) {
                mainTextArea.setText("");
            }
            mainTextArea.append("Solution " + solutionNumber + ":\n");
            PrintBox.printBox(puzzle.getBox(), mainTextArea);
            mainTextArea.append("\n");
        }

        public void piecePlaced(Puzzle puzzle) {
            //do nothing
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox logCheckBox;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextArea mainTextArea;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JCheckBox oneSolutionCheckBox;
    private javax.swing.JMenuItem openPuzzleMenuItem;
    private javax.swing.JMenu puzzleMenu;
    private javax.swing.JMenuItem saveOutputMenuItem;
    private javax.swing.JCheckBox showPlacementsCheckBox;
    private javax.swing.JButton solveButton;
    private javax.swing.JMenuItem solvePuzzleItem;
    // End of variables declaration//GEN-END:variables
    final static private boolean dragNdrop = true;
    final static private boolean printPlacements = false;
    private boolean solving = true;
    private Puzzle currentPuzzle;
    private File selectedFile;
    private File outputFile;
    private Move currentMove = null;
    private Thread thread;
    private Point dragPoint;
    private Point offset;
}
