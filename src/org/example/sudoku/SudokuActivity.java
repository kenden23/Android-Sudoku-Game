package org.example.sudoku;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class SudokuActivity extends ActionBarActivity implements
		OnClickListener {
	private static final String TAG = "Sudoku";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sudoku);

		// set up click listeners for all the buttons
		View continueButton = findViewById(R.id.continue_button);
		continueButton.setOnClickListener(this);

		View newButton = findViewById(R.id.new_button);
		newButton.setOnClickListener(this);

		View aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(this);

		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Music.play(this, R.raw.main);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Music.stop(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.continue_button:
			startGame(GameActivity.DIFFICULTY_CONTINUE);
			break;
		case R.id.about_button:
			Intent i = new Intent(this, About.class);
			startActivity(i);
			break;
		case R.id.new_button:
			openNewGameDialog();
			break;
		case R.id.exit_button:
			finish();
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.sudoku, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.settings:
			startActivity(new Intent(this, Prefs.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Ask the user what difficulty level they want */
	private void openNewGameDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.new_game_title)
				.setItems(R.array.difficulty,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								startGame(i);
							}
						}).show();
	}

	/** Start a new game with the given difficulty level */
	private void startGame(int i) {
		Log.d(TAG, "clicked on " + i);
		// Start game here...
		//Intent intent = new Intent(SudokuActivity.this, GameActivity.class);
		//intent.putExtra(GameActivity.KEY_DIFFICULTY, i);
		//startActivity(intent);
		
		//equal above three lines, and equal to add () to (new Intent(this, GameActivity.class)).putExtra(...));
		startActivity(new Intent(this, GameActivity.class).putExtra(GameActivity.KEY_DIFFICULTY, i));
	}
}
