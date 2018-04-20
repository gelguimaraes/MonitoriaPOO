import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class TelaPrincipal {

	private JFrame janelaJogo;
	private JLabel tituloJogo; 
	private JTextField campoNumero;
	private JButton botaoJogar;
	private JButton botaoReiniciar;
	private JLabel textoDescricao;
	private JTextArea resultado;
	private JLabel background;
	private JList<String> ultimosNumeros;
	DefaultListModel<String> model;
	private Jogo jogo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.janelaJogo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
		jogo = new Jogo();
		try { 
		    Font.createFont(Font.TRUETYPE_FONT,
		    		TelaPrincipal.class.getClassLoader().getResourceAsStream("font/TEKTONPRO-BOLD.OTF")); 
		} catch (Exception e) { 
		    e.printStackTrace();
		}
		
	}
	

	public int ReiniciaJogo() {
		jogo.setCont(0); //zera contador
		campoNumero.setText(""); // limpa campo numero
		resultado.setText(""); //limpa campo resultado
		model.clear(); // limpa lista ultimos sorteados
		jogo.setFim(false); // seta variavel fim como false
		int[]jogados = {-1, -1, -1, -1, -1, -1, -1};
		jogo.setJogados(jogados);
		int n = jogo.geraNewNum();
		return n;
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		janelaJogo = new JFrame();
		janelaJogo.getContentPane().setBackground(new Color(0, 128, 128));
		janelaJogo.getContentPane().setLayout(null);
		
		
		campoNumero = new JTextField();
		campoNumero.setBorder(new LineBorder(new Color(0, 128, 128), 3));
		campoNumero.setFont(new Font("Tekton Pro", Font.BOLD, 21));
		campoNumero.setForeground(new Color(138, 43, 226));
		campoNumero.setBackground(new Color(255, 255, 255));
		campoNumero.setHorizontalAlignment(SwingConstants.CENTER);
		campoNumero.setBounds(155, 101, 92, 48);
		campoNumero.setColumns(2);
		janelaJogo.getContentPane().add(campoNumero); //adiciona o TextField
		
		
		tituloJogo = new JLabel("Jogo de Adivinha\u00E7\u00E3o ");
		tituloJogo.setHorizontalAlignment(SwingConstants.CENTER);
		tituloJogo.setForeground(new Color(255, 255, 255));
		tituloJogo.setFont(new Font("Tekton Pro", Font.BOLD, 33));
		tituloJogo.setBounds(46, 11, 335, 53);
		janelaJogo.getContentPane().add(tituloJogo);
		
		textoDescricao = new JLabel("Adivinhe um n\u00FAmero entre 0 e 99, voc\u00EA tem 6 tentativas!");
		textoDescricao.setFont(new Font("Tekton Pro", Font.PLAIN, 15));
		textoDescricao.setForeground(new Color(255, 255, 255));
		textoDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		textoDescricao.setBounds(10, 63, 406, 14);
		janelaJogo.getContentPane().add(textoDescricao);
		

		resultado = new JTextArea();
		resultado.setLineWrap(true);
		resultado.setWrapStyleWord(true);
		resultado.setForeground(new Color(255, 255, 0));
		resultado.setFont(new Font("Tekton Pro", Font.BOLD, 24));
		resultado.setBounds(29, 222, 240, 113);	
		resultado.setBackground(new Color(0,128,128,0));
		resultado.setOpaque(false);
		janelaJogo.getContentPane().add(resultado);
		
		model = new DefaultListModel<String>();
		
		ultimosNumeros = new JList<String>(model);
		ultimosNumeros.setBounds(277, 222, 133, 113);
		ultimosNumeros.setBorder(null);
		ultimosNumeros.setForeground(new Color(255, 165, 0));
		ultimosNumeros.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		ultimosNumeros.setBackground(new Color(0, 128, 128));
		janelaJogo.getContentPane().add(ultimosNumeros);
		
		janelaJogo.setBackground(new Color(255, 255, 255));
		janelaJogo.setTitle("Jogo de Adivinha\u00E7\u00E3o");
		janelaJogo.setBounds(100, 100, 436, 400);
		janelaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		botaoJogar = new JButton("Jogar");
		botaoJogar.setFont(new Font("Tekton Pro", Font.BOLD, 20));
		botaoJogar.setBackground(new Color(240, 255, 255));
		botaoJogar.setForeground(new Color(148, 0, 211));
		botaoJogar.setBounds(114, 160, 92, 38);
		botaoJogar.addActionListener(new ActionListener() {//classe anonima
			public void actionPerformed(ActionEvent arg0) {
				int num =  Integer.parseInt(campoNumero.getText());
				if (jogo.validaNum(num) && !jogo.verificaRepetido(num)) {
					resultado.setText(jogo.verificaSorteio(num));
					if(jogo.getCont() <=6 ) {
						model.add(0,"Último número: " + num);
						campoNumero.setText("");
					}
					else if(jogo.isFim() || jogo.getCont()==6) {
						JOptionPane.showMessageDialog(null, "O Jogou finalizou, clique OK para jogar novamente!");
						jogo.setN(ReiniciaJogo());
					}
				}else
					resultado.setText("Número Inválido tente de novo!");
			}
		});
		janelaJogo.getContentPane().add(botaoJogar);  //Adiciona o botao 
		
		botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.setBackground(new Color(240, 255, 255));
		botaoReiniciar.setForeground(Color.RED);
		botaoReiniciar.setFont(new Font("Tekton Pro", Font.BOLD, 14));
		botaoReiniciar.setBounds(216, 160, 85, 38);
		botaoReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jogo.setN(ReiniciaJogo());
			}
		});
		janelaJogo.getContentPane().add(botaoReiniciar);
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/img/interrogacao.png")));
		background.setBounds(0, 0, 426, 367);
		janelaJogo.getContentPane().add(background);
	}
}
