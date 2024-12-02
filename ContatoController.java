package com.exemplo.agenda.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.exemplo.agenda.view.ContatoView;
import com.exemplo.agenda.model.ContatoModel;
import com.exemplo.agenda.model.Contato;


public class ContatoController {

    private ContatoView view;
    private ContatoModel model;

    public ContatoController(ContatoView view, ContatoModel model) {
        this.view = view;
        this.model = model;
        inicializarAcoes();
    }

    private void inicializarAcoes() {
        // Ação do botão Adicionar
        view.getbtnAdicionar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = view.getTxtNome().getText();
                String telefone = view.getTxtTelefone().getText();
                if (nome.isEmpty() || telefone.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nome e telefone não podem ser vazios!");
                    return;
                }
                model.adicionarContato(nome, telefone);
                JOptionPane.showMessageDialog(view, "Contato adicionado com sucesso!");
            }
        });

        // Ação do botão Atualizar
        view.getbtnAtualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTabelaContatos().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Selecione um contato para atualizar!");
                    return;
                }
                int id = Integer.parseInt(view.getModel().getValueAt(selectedRow, 0).toString());
                String nome = view.getTxtNome().getText();
                String telefone = view.getTxtTelefone().getText();
                if (nome.isEmpty() || telefone.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nome e telefone não podem ser vazios!");
                    return;
                }
                model.atualizarContato(id, nome, telefone);
                JOptionPane.showMessageDialog(view, "Contato atualizado com sucesso!");
            }
        });

        // Ação do botão Deletar
        view.getbtnDeletar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = view.getTabelaContatos().getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(view, "Selecione um contato para deletar!");
                    return;
                }
                int id = Integer.parseInt(view.getModel().getValueAt(selectedRow, 0).toString());
                model.deletarContato(id);
                JOptionPane.showMessageDialog(view, "Contato deletado com sucesso!");
            }
        });

        // Ação do botão Listar
        view.getbtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Contato> contatos = model.listarContatos();
                DefaultTableModel tableModel = view.getModel();
                tableModel.setRowCount(0); // Limpa a tabela
                for (Contato contato : contatos) {
                    tableModel.addRow(new Object[] { contato.getId(), contato.getNome(), contato.getTelefone() });
                }
            }
        });
    }
}
