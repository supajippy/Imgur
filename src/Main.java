import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;



public class Main extends Application {
    public static void main(String[] args) {launch(args); }

    Image image11 = new Image("file:dylalal.jpg");
    Image image22= new Image("file:dylan3.jpg");
    Image image33= new Image("file:jo.jpg");
    Image defaut= new Image("file:dyllla.jpg");

    ImageView imageView;

    Label bas= new Label("Bienvenue dans le modificateur d'images!");

    ColorAdjust imageColors;
    Slider luminosite;
    Slider contraste;
    Slider teinte;
    Slider saturation;



    @Override
    public void start(Stage primarystage){

        imageView= new ImageView(defaut);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);
        imageView.setFitHeight(500);

    BorderPane bord= creemenu();

    bord.setBottom(bas);
    primarystage.setMaximized(true);
    primarystage.setScene(new Scene(bord));
    primarystage.show();

    }

    public BorderPane creemenu(){

       MenuItem reinitialiser= new MenuItem("Reinitialiser");
        MenuItem image1=new MenuItem("image 1");
        MenuItem image2=new MenuItem("image 2");
        MenuItem image3= new MenuItem("image 3");

        image1.setOnAction(event -> {imageView.setImage(image11);bas.setText("Image 1 chargée!");});
        image2.setOnAction(event -> {imageView.setImage(image22);bas.setText("Image 2 chargée!");});
        image3.setOnAction(event -> {imageView.setImage(image33);bas.setText("Image 3 chargée!");});
        reinitialiser.setOnAction(event -> {reinitialiser();bas.setText("Image réinitialisée!");});

        Menu fichiers = new Menu("Fichiers");
        Menu charger=new Menu("Charger");
        Menu actions= new Menu("Actions");

        actions.getItems().addAll(reinitialiser);
        fichiers.getItems().addAll(charger);
        charger.getItems().addAll(image1,image2,image3);

        MenuBar menuBar= new MenuBar(fichiers,actions);
        ContextMenu clic=new ContextMenu(fichiers,actions);

        BorderPane bord=new BorderPane();
        bord.setTop(menuBar);

        bord.setCenter(creersliders());
        bord.getCenter().setOnContextMenuRequested(event -> {clic.show(bord.getCenter(),event.getScreenX(),event.getScreenY());});
        return bord;
    }

    public HBox creersliders(){

        imageColors = new ColorAdjust();

        Label textluminosite= new Label();
        textluminosite.setText("Luminosité");

        Label textcontraste= new Label();
        textcontraste.setText("Contraste");

        Label textteinte=new Label();
        textteinte.setText("Teinte");

        Label textsaturation=new Label();
        textsaturation.setText("Saturation");

        luminosite=new Slider(-1,1,0);
        luminosite.setOnMouseDragged(event -> imageColors.setBrightness(luminosite.getValue()));
        Tooltip lum=new Tooltip("Rend l'image plus claire ou plus sombre");
        luminosite.setTooltip(lum);

        contraste=new Slider(-1,1,0);
        contraste.setOnMouseDragged(event -> imageColors.setContrast(contraste.getValue()));
        Tooltip cont= new Tooltip("Diminue ou augmente la différence entre les couleurs");
        contraste.setTooltip(cont);

        teinte=new Slider(-1,1,0);
        teinte.setOnMouseDragged(event -> imageColors.setHue(teinte.getValue()));
        Tooltip tein=new Tooltip("Change la teinte (couleur) de l'image");
        teinte.setTooltip(tein);

        saturation=new Slider(-1,1,0);
        saturation.setOnMouseDragged(event -> imageColors.setSaturation(saturation.getValue()));
        Tooltip satan=new Tooltip("Diminue ou augmente l'intensité des couleurs");
        saturation.setTooltip(satan);

        imageView.setEffect(imageColors);


        VBox vneck= new VBox(textluminosite,luminosite,textcontraste,contraste,textteinte,teinte,textsaturation,saturation);
        vneck.setAlignment(Pos.CENTER);
        vneck.setSpacing(10);

        HBox hb= new HBox(imageView,vneck);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(10);

        return hb;
    }
    public void reinitialiser(){

        imageColors.setSaturation(0);
        imageColors.setHue(0);
        imageColors.setContrast(0);
        imageColors.setBrightness(0);
        saturation.setValue(0);
        teinte.setValue(0);
        contraste.setValue(0);
        luminosite.setValue(0);


    }
}
