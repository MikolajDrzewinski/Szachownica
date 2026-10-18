package com.example.szachownica;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Math.min;

public class MainActivity extends AppCompatActivity {

    private Panel widokSzachownicyPanel;
    private Paint pedzelPolaJasnego = new Paint();
    private Paint pedzelPolaCiemnego = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pedzelPolaJasnego.setColor(Color.WHITE);
        pedzelPolaCiemnego.setColor(Color.BLACK);

        widokSzachownicyPanel = new Panel(this);
        setContentView(widokSzachownicyPanel);

        // Rejestracja menu kontekstowego dla panelu widoku szachownicy
        registerForContextMenu(widokSzachownicyPanel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuGlowne) {
        MenuInflater inflaterGlownegoMenu = getMenuInflater();
        inflaterGlownegoMenu.inflate(R.menu.main, menuGlowne);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem wybranyElementMenu) {
        int idOpcjiGlownej = wybranyElementMenu.getItemId();

        if (idOpcjiGlownej == R.id.menu_tworca) {
            Toast.makeText(getApplicationContext(), "Twórca: Brajan", Toast.LENGTH_SHORT).show();
            return true;
        } else if (idOpcjiGlownej == R.id.menu_wyjscie) {
            finish();
            return true;
        } else if (idOpcjiGlownej == R.id.kolor_czarno_bialy) {
            pedzelPolaJasnego.setColor(Color.WHITE);
            pedzelPolaCiemnego.setColor(Color.BLACK);
            widokSzachownicyPanel.postInvalidate();
            return true;
        } else if (idOpcjiGlownej == R.id.kolor_czerwono_zolty) {
            pedzelPolaJasnego.setColor(Color.RED);
            pedzelPolaCiemnego.setColor(Color.YELLOW);
            widokSzachownicyPanel.postInvalidate();
            return true;
        }

        return super.onOptionsItemSelected(wybranyElementMenu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menuKontekstowe, View widok, ContextMenu.ContextMenuInfo informacjeOView) {
        super.onCreateContextMenu(menuKontekstowe, widok, informacjeOView);
        menuKontekstowe.setHeaderTitle("Menu Kontekstowe Szachownicy");
        MenuInflater inflaterKontekstowy = getMenuInflater();
        inflaterKontekstowy.inflate(R.menu.main, menuKontekstowe);
    }

    @Override
    public boolean onContextItemSelected(MenuItem wybranyElementKontekstowy) {
        int idOpcjiKontekstowej = wybranyElementKontekstowy.getItemId();

        if (idOpcjiKontekstowej == R.id.id_kontekst_niebiesko_bialy) {
            pedzelPolaJasnego.setColor(Color.WHITE);
            pedzelPolaCiemnego.setColor(Color.BLUE);
            widokSzachownicyPanel.postInvalidate();
            Toast.makeText(getApplicationContext(), "Kontekst: Wybrano niebiesko-białą kolorystykę", Toast.LENGTH_SHORT).show();
            return true;
        } else if (idOpcjiKontekstowej == R.id.id_kontekst_zielono_zolty) {
            pedzelPolaJasnego.setColor(Color.YELLOW);
            pedzelPolaCiemnego.setColor(Color.GREEN);
            widokSzachownicyPanel.postInvalidate();
            Toast.makeText(getApplicationContext(), "Kontekst: Wybrano zielono-żółtą kolorystykę", Toast.LENGTH_SHORT).show();
            return true;
        } else if (idOpcjiKontekstowej == R.id.id_kontekst_reset_kolorow) {
            pedzelPolaJasnego.setColor(Color.WHITE);
            pedzelPolaCiemnego.setColor(Color.BLACK);
            widokSzachownicyPanel.postInvalidate();
            Toast.makeText(getApplicationContext(), "Kontekst: Przywrócono kolory domyślne", Toast.LENGTH_SHORT).show();
            return true;
        } else if (idOpcjiKontekstowej == R.id.menu_tworca) {
            Toast.makeText(getApplicationContext(), "Twórca: Brajan", Toast.LENGTH_SHORT).show();
            return true;
        } else if (idOpcjiKontekstowej == R.id.menu_wyjscie) {
            finish();
            return true;
        } else if (idOpcjiKontekstowej == R.id.kolor_czarno_bialy) {
            pedzelPolaJasnego.setColor(Color.WHITE);
            pedzelPolaCiemnego.setColor(Color.BLACK);
            widokSzachownicyPanel.postInvalidate();
            return true;
        } else if (idOpcjiKontekstowej == R.id.kolor_czerwono_zolty) {
            pedzelPolaJasnego.setColor(Color.RED);
            pedzelPolaCiemnego.setColor(Color.YELLOW);
            widokSzachownicyPanel.postInvalidate();
            return true;
        }

        return super.onContextItemSelected(wybranyElementKontekstowy);
    }

    class Panel extends View {
        public Panel(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvasPlochy) {
            super.onDraw(canvasPlochy);

            canvasPlochy.drawColor(Color.GRAY);

            float szerokoscSiatki = getWidth();
            float wysokoscSiatki = getHeight();

            float rozmiarSzachownicy = min(szerokoscSiatki, wysokoscSiatki);
            float rozmiarJednegoPola = rozmiarSzachownicy / 8f;

            float przesunieciePoziomeX = (szerokoscSiatki - rozmiarSzachownicy) / 2f;
            float przesunieciePionoweY = (wysokoscSiatki - rozmiarSzachownicy) / 2f;

            for (int kolumna = 0; kolumna < 8; kolumna++) {
                for (int wiersz = 0; wiersz < 8; wiersz++) {
                    float lewyX = przesunieciePoziomeX + kolumna * rozmiarJednegoPola;
                    float gornyY = przesunieciePionoweY + wiersz * rozmiarJednegoPola;
                    float prawyX = lewyX + rozmiarJednegoPola;
                    float dolnyY = gornyY + rozmiarJednegoPola;

                    if ((kolumna + wiersz) % 2 == 0) {
                        canvasPlochy.drawRect(
                                lewyX,
                                gornyY,
                                prawyX,
                                dolnyY,
                                pedzelPolaJasnego
                        );
                    } else {
                        canvasPlochy.drawRect(
                                lewyX,
                                gornyY,
                                prawyX,
                                dolnyY,
                                pedzelPolaCiemnego
                        );
                    }
                }
            }
        }
    }
}