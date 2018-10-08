package br.com.livrospockframework.app

import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import spock.lang.Specification;

class TelaInicialTest extends Specification {

    TelaInicial telaInicial
    EditText editNum1
    EditText editNum2
    TextView tvResultado

    def setup() {
        telaInicial = new TelaInicial()

        editNum1 = Mock(EditText)
        editNum2 = Mock(EditText)
        tvResultado = Mock(TextView)

        editNum1.getText() >> Mock(Editable)
        editNum2.getText() >> Mock(Editable)

        telaInicial.editNum1 = editNum1
        telaInicial.editNum2 = editNum2
        telaInicial.tvResultado = tvResultado
    }

    void 'deveria dividir'() {
        given:
        def numero1  = 10
        def numero2  = 20
        editNum1.text.toString() >> numero1.toString()
        editNum2.text.toString() >> numero2.toString()

        when:
        telaInicial.dividir(Mock(Button))

        then:
        1 * tvResultado.setText((numero1/numero2).toString())
    }

    void 'não deveria dividir por Zero'() {
       given:
       editNum1.text.toString() >> "10"
       editNum2.text.toString() >> "0"

       when:
       telaInicial.dividir(Mock(Button))

       then:
       1 * tvResultado.setText("Não sei dividir por 0")
    }

}