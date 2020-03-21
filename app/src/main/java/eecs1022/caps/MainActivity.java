package eecs1022.caps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int scores=0;
    private int count=1;
    private String result="";
    Button done;
    Game game=new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);

        done=findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doneClicked(view);
            }
        });
    }

    public void doneClicked(View v){

        TextView numView=findViewById(R.id.qNum);
        String qNum="Q#"+count;
        numView.setText(qNum);
        if (count>9) {
            String over = "Game Over";
            numView.setText(over);
            done.setEnabled(false);
        }
        count++;

        TextView scoreView=findViewById(R.id.score);
        String score="SCORE "+scores;
        scoreView.setText(score);
        if (scores>8)
            scores=8;

        TextView quesView=findViewById(R.id.question);
        String qa[]=game.qa().split("\n",2);

        quesView.setText(qa[0]);

        EditText ansView=findViewById(R.id.answer);
        String answer=ansView.getText().toString();

        if(answer.toLowerCase().equals(qa[1].toLowerCase()))
            scores++;

        result+=qNum+qa[0]+"\n"
                +"Your answer: "
                +answer
                +"\n"
                +"Correct answer: "
                +qa[1]
                +"\n\n";

        ((TextView)findViewById(R.id.log)).setText(result);
    }

}
