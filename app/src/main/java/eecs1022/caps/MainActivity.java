package eecs1022.caps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        try {
            TextView numView=findViewById(R.id.qNum);
            TextView scoreView=findViewById(R.id.score);
            TextView quesView=findViewById(R.id.question);
            EditText ansView=findViewById(R.id.answer);

            String qNum="Q#"+count;
            String answer=ansView.getText().toString();
            String[] qa =game.qa().split("\n",2);

            if(answer.toLowerCase().equals(qa[1].toLowerCase()))
                scores++;

            String score="SCORE "+scores;

            numView.setText(qNum);
            scoreView.setText(score);
            quesView.setText(qa[0]);

            if (count>8) {
                String over = "Game Over";
                numView.setText(over);
                done.setEnabled(false);
            }
            count++;

            if (scores>8)
                scores=8;

            result+=qNum+qa[0]+"\n"
                    +"Your answer: "
                    +answer
                    +"\n"
                    +"Correct answer: "
                    +qa[1]
                    +"\n\n";

            ((TextView)findViewById(R.id.log)).setText(result);
        }

        catch (Exception e){
            Toast toast=Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
