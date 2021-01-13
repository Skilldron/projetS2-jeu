package sample;
import javafx.animation.PathTransition;
import javafx.application.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Path;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.text.TextFlow;
import javafx.animation.KeyFrame;
import javafx.scene.input.KeyCode;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.scene.text.FontWeight;
import javafx.scene.media.AudioClip;

public class Main extends Application {
    private Pane _root;
    private boolean lancerVideo=false;
    private boolean x=false;
    private boolean tableau1Enleve=false;
    private int bouton1 =0;
    private int bouton2 =0;
    private boolean lockpot=false;
    private int bouton3 =0;
    private int bouton4 = 0;
    private ImageView[] boutons1=new ImageView[5];
    private ImageView[] boutons2=new ImageView[5];
    private ImageView[] boutons3=new ImageView[5];
    private ImageView[] boutons4=new ImageView[5];
    private ImageView[] becs=new ImageView[4];
    private boolean portesoulevee=false;
    private boolean ordizoom=false;
    private boolean becherplace=false;
    private boolean vuelv1=false;
    private boolean vuelv2=false;
    private boolean msgstart=false;
    private int etatbecher=0;
    private boolean  instructmsg=false;
    boolean finale=false;
    private boolean tabper=false;
    boolean lv3rdy=false;
    private boolean noticeup=false;
    private boolean tambourjoué=false;
    private String[] bec=new String[3];

    @Override
    public void start(Stage primaryStage) {
        AudioClip error=new AudioClip(Main.class.getResource("error.mp3").toExternalForm());
        AudioClip correct=new AudioClip(Main.class.getResource("correct.mp3").toExternalForm());
        AudioClip papier = new AudioClip(Main.class.getResource("papier.mp3").toExternalForm());
        AudioClip papierev = new AudioClip(Main.class.getResource("papierrev.mp3").toExternalForm());
        papier.setVolume(0.5);
        papierev.setVolume(0.5);
        AudioClip tambour = new AudioClip(Main.class.getResource("tambour.mp3").toExternalForm());
        AudioClip door = new AudioClip(Main.class.getResource("door.mp3").toExternalForm());  //Importation des ressources dans le projet
        door.setVolume(0.5);  //Changement de volume de musique
        //######################## ECRAN TITRE #####################
        MediaPlayer titlemusic=new MediaPlayer(new Media(getClass().getResource("titlemusic.mp3").toExternalForm()));
        titlemusic.setMute(false);
        titlemusic.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView titmus = new MediaView(titlemusic); // Ajout de la musique d'ecran titre
        StackPane titlesc = new StackPane();
        Image titleimg = new Image(getClass().getResource("title.jpg").toExternalForm()); //Chargement d'une image
        ImageView viewtitle = new ImageView(titleimg);
        viewtitle.setX(500);
        titlesc.getChildren().add(titmus);
        titlesc.getChildren().add(viewtitle);
        Scene sceneTitle = new Scene(titlesc, 1280, 720);
        titlemusic.play();
        //################### FIN ECRAN TITRE ###################

        //######################## VIDEO INTRO #########################
        StackPane introsc = new StackPane();
        MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("intro.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);
        Text text1 = new Text("Press a key to skip intro");
        text1.setFont(Font.font("Helvetica",FontWeight.BOLD,30));
        text1.setFill(Color.GREY);
        TextFlow skip=new TextFlow();
        skip.getChildren().add(text1);
        introsc.getChildren().add(mediaView);
        introsc.getChildren().add(skip);
        Scene sceneintro = new Scene(introsc, 1280, 720);
        primaryStage.setScene(sceneTitle);
        primaryStage.show();
        //################### FIN VIDEO INTRO ###################
        sceneTitle.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> { // Detecte lorsqu'une touche est relachée
            titlemusic.stop(); // arrete la musique d'ecran titre
            tambour.play(); // Joue un son
            primaryStage.setScene(sceneintro); //Passe a la video d'intro
            primaryStage.show();
            player.play();
            lancerVideo=true;
        });

        //######################## SALLE 1 #######################
        //Bouton1
        Image symbol1=new Image(getClass().getResource("code1.png").toExternalForm());
        Image symbol2=new Image(getClass().getResource("code2.png").toExternalForm());
        Image symbol3=new Image(getClass().getResource("code3.png").toExternalForm());
        Image symbol4=new Image(getClass().getResource("code4.png").toExternalForm());
        Image symbol5=new Image(getClass().getResource("code5.png").toExternalForm());
        boutons1[0]=new ImageView(symbol1);
        boutons1[1]=new ImageView(symbol2);
        boutons1[2]=new ImageView(symbol3);
        boutons1[3]=new ImageView(symbol4);
        boutons1[4]=new ImageView(symbol5);
        //Bouton2

        boutons2[0]=new ImageView(symbol1);
        boutons2[1]=new ImageView(symbol2);
        boutons2[2]=new ImageView(symbol3);
        boutons2[3]=new ImageView(symbol4);
        boutons2[4]=new ImageView(symbol5);
        //Bouton3
        boutons3[0]=new ImageView(symbol1);
        boutons3[1]=new ImageView(symbol2);
        boutons3[2]=new ImageView(symbol3);
        boutons3[3]=new ImageView(symbol4);
        boutons3[4]=new ImageView(symbol5);
        //Bouton 4

        boutons4[0]=new ImageView(symbol1);
        boutons4[1]=new ImageView(symbol2);
        boutons4[2]=new ImageView(symbol3);
        boutons4[3]=new ImageView(symbol4);
        boutons4[4]=new ImageView(symbol5);


        Image prte = new Image(getClass().getResource("porte.png").toExternalForm());
        Pane salle1 = new Pane();
        Image imgsalle1 = new Image(getClass().getResource("salle1.png").toExternalForm()); //Importation des images dans le projet
        ImageView porte=new ImageView(prte);
        Image tablo=new Image(getClass().getResource("tab.png").toExternalForm());
        Image hp = new Image(getClass().getResource("hautporte.png").toExternalForm());
        ImageView HautPorte=new ImageView(hp);
        ImageView tableau =new ImageView(tablo);
        HautPorte.setLayoutX(439);
        HautPorte.setLayoutY(0);   // Définition de toutes les coordonnées des differents objets
        tableau.setLayoutX(63);
        tableau.setLayoutY(223);
        porte.setLayoutX(470);
        porte.setLayoutY(166);
        Path p2= new Path();
        PathTransition pt2=new PathTransition(Duration.millis(2300),p2);
        p2.getElements().add(new MoveTo(638, 300));  //Définition d'un chemin d'animation
        p2.getElements().add(new LineTo(638, 200));
        Path p = new Path();
        p.getElements().add(new MoveTo(185, 290));  //Définition d'un chemin d'animation
        p.getElements().add(new LineTo(185, -350));
        PathTransition pt=new PathTransition(Duration.millis(2300),p);
        pt.setNode(porte);
        ImageView views1 = new ImageView(imgsalle1);
        salle1.getChildren().add(views1);
        salle1.getChildren().add(porte);   //Ajout des differents objets
        salle1.getChildren().add(tableau);
        salle1.getChildren().add(HautPorte);
        Scene scenes1 = new Scene(salle1, 1280, 720);
        //################### FIN SALLE 1###################

        // ################### SALLE 2 ################
        Pane salle2 = new Pane();
        Image imgsalle2 = new Image(getClass().getResource("salle2.png").toExternalForm());
        ImageView views2 = new ImageView(imgsalle2);
        Image portefond = new Image(getClass().getResource("portefond.png").toExternalForm());
        ImageView ps2 = new ImageView(portefond);
        ps2.setX(569);
        ps2.setY(282);
        pt2.setNode(ps2);
        Image lv1 = new Image(getClass().getResource("LV1.png").toExternalForm());
        ImageView vlv1 = new ImageView(lv1);
        Image lv2 = new Image(getClass().getResource("LV2.png").toExternalForm());
        ImageView vlv2 = new ImageView(lv2);
        Image hporte2 = new Image(getClass().getResource("hautp2.png").toExternalForm());
        ImageView hp2 = new ImageView(hporte2);
        vlv1.setX(200);
        vlv2.setX(200);
        vlv1.setY(150);
        vlv2.setY(150);
        hp2.setY(20);
        salle2.getChildren().add(views2);
        salle2.getChildren().add(ps2);
        salle2.getChildren().add(hp2);
        Text text2 = new Text("Put the mouse in the \"START\" rectangle");
        text2.setFont(Font.font("Helvetica",FontWeight.BOLD,50));
        text2.setFill(Color.RED);
        TextFlow start=new TextFlow();
        start.getChildren().add(text2);
        //################### FIN SALLE 2###################

        //################### SALLE 3 ######################
        Pane salle3 = new Pane();
        Image potf=new Image(getClass().getResource("bechf.png").toExternalForm());
        Image p19=new Image(getClass().getResource("19.png").toExternalForm());
        Image p69=new Image(getClass().getResource("69.png").toExternalForm());
        Image p80=new Image(getClass().getResource("80.png").toExternalForm());
        Image p14=new Image(getClass().getResource("p14.png").toExternalForm());
        Image p17=new Image(getClass().getResource("p17.png").toExternalForm());
        Image bech=new Image(getClass().getResource("petitb.png").toExternalForm());
        Image bechere=new Image(getClass().getResource("becherg.png").toExternalForm());
        ImageView potionf=new ImageView(potf);
        ImageView x19=new ImageView(p19);
        ImageView x69=new ImageView(p69);
        ImageView x80=new ImageView(p80);
        ImageView x14=new ImageView(p14);
        ImageView x17=new ImageView(p17);
        x14.setX(327);
        x14.setY(114);
        x80.setX(780);
        x80.setY(312);
        x19.setX(907);
        x19.setY(316);
        x17.setX(820);
        x17.setY(250);
        x69.setX(696);
        x69.setY(245);
        ImageView becherz=new ImageView(bech);
        ImageView Becher=new ImageView(bechere);
        Becher.setX(815);
        Becher.setY(330);
        Image notic=new Image(getClass().getResource("notice.png").toExternalForm());
        Image tabp=new Image(getClass().getResource("tablo.png").toExternalForm());
        Image imgsalle3 = new Image(getClass().getResource("salle3.png").toExternalForm());
        ImageView views3 = new ImageView(imgsalle3);
        ImageView period=new ImageView(tabp);
        ImageView notice=new ImageView(notic);
        notice.setX(497);
        notice.setY(140);
        period.setX(200);
        period.setY(100);
        Image bech1=new Image(getClass().getResource("bech1.png").toExternalForm());
        Image bech2=new Image(getClass().getResource("bech2.png").toExternalForm());
        Image bech3=new Image(getClass().getResource("bech3.png").toExternalForm());
        ImageView b1=new ImageView(bech1);
        ImageView b2=new ImageView(bech2);
        ImageView b3=new ImageView(bech3);
        b1.setX(815);
        b1.setY(330);
        b2.setX(815);
        potionf.setX(815);
        potionf.setY(330);
        becherz.setX(960);
        becherz.setY(116);
        b2.setY(330);
        b3.setX(815);
        b3.setY(330);
        becs[0]=Becher;
        becs[1]=b1;
        becs[2]=b2;
        becs[3]=b3;
        salle3.getChildren().add(views3);
        salle3.getChildren().addAll(x17,x14,x19,x69,x80,becherz);
        //################## FIN SALLE 3 #####################

        //DETECTIONS SOURIS SALLE 1
        sceneintro.addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> { //Detecte lorqu'une touche est relachée
            if(lancerVideo==true) {
                player.stop(); //Arrete la video d'intro.
                primaryStage.setScene(scenes1); //Passe a la scene suivante (salle 1)
                primaryStage.show();
            }
        });


        salle1.setOnMouseClicked((evt) -> {
            if((evt.getX()<320 && evt.getX()>60)&&(evt.getY()<405 && evt.getY()>200)){
                if (tableau1Enleve==false) {
                    papier.play();
                    salle1.getChildren().remove(tableau);
                    tableau1Enleve = true;
                }
            }
            if((evt.getX()<941 && evt.getX()>913)&&(evt.getY()<473 && evt.getY()>449)) {//Bouton 1
                boutons1[0].setLayoutX(913);
                boutons1[1].setLayoutX(913);
                boutons1[2].setLayoutX(915);
                boutons1[3].setLayoutX(918);
                boutons1[4].setLayoutX(913);
                boutons1[0].setLayoutY( 449);
                boutons1[1].setLayoutY(449);
                boutons1[2].setLayoutY(449);
                boutons1[3].setLayoutY(449);
                boutons1[4].setLayoutY(449);
                for(int i=0;i<5;i++){
                    if (salle1.getChildren().contains(boutons1[i])){
                        salle1.getChildren().remove(boutons1[i]);
                    }
                }
                if (bouton1!=5){
                    bouton1++;
                    salle1.getChildren().add(boutons1[bouton1-1]);
                }
                else{
                    bouton1=0;
                }


            }
            if((evt.getX()<972 && evt.getX()>945)&&(evt.getY()<473 && evt.getY()>449)) {//Bouton 2
                boutons2[0].setLayoutX(943);
                boutons2[1].setLayoutX(943);
                boutons2[2].setLayoutX(945);
                boutons2[3].setLayoutX(948);
                boutons2[4].setLayoutX(943);
                boutons2[0].setLayoutY(449);
                boutons2[1].setLayoutY(449);
                boutons2[2].setLayoutY(449);
                boutons2[3].setLayoutY(449);
                boutons2[4].setLayoutY(449);
                for(int i=0;i<5;i++){
                    if (salle1.getChildren().contains(boutons2[i])){
                        salle1.getChildren().remove(boutons2[i]);
                    }
                }
                if (bouton2!=5){
                    bouton2++;
                    salle1.getChildren().add(boutons2[bouton2-1]);

                }
                else{
                    bouton2=0;
                }

            }
            if((evt.getX()<1001 && evt.getX()>975)&&(evt.getY()<473 && evt.getY()>449)) {//Bouton 3
                boutons3[0].setLayoutX(973);
                boutons3[1].setLayoutX(973);
                boutons3[2].setLayoutX(975);
                boutons3[3].setLayoutX(978);
                boutons3[4].setLayoutX(973);
                boutons3[0].setLayoutY(449);
                boutons3[1].setLayoutY(449);
                boutons3[2].setLayoutY(449);
                boutons3[3].setLayoutY(449);
                boutons3[4].setLayoutY(449);
                for(int i=0;i<5;i++){
                    if (salle1.getChildren().contains(boutons3[i])){
                        salle1.getChildren().remove(boutons3[i]);
                    }
                }
                if (bouton3!=5){
                    bouton3++;
                    salle1.getChildren().add(boutons3[bouton3-1]);
                }
                else{
                    bouton3=0;
                }

            }
            if((evt.getX()<1032 && evt.getX()>1005)&&(evt.getY()<473 && evt.getY()>449)) {//Bouton 4
                boutons4[0].setLayoutX(1003);
                boutons4[1].setLayoutX(1003);
                boutons4[2].setLayoutX(1005);
                boutons4[3].setLayoutX(1008);
                boutons4[4].setLayoutX(1003);
                boutons4[0].setLayoutY(449);
                boutons4[1].setLayoutY(449);
                boutons4[2].setLayoutY(449);
                boutons4[3].setLayoutY(449);
                boutons4[4].setLayoutY(449);

                for(int i=0;i<5;i++){
                    if (salle1.getChildren().contains(boutons4[i])){
                        salle1.getChildren().remove(boutons4[i]);
                    }
                }
                if (bouton4!=5){
                    bouton4++;
                    salle1.getChildren().add(boutons4[bouton4-1]);
                }
                else{
                    bouton4=0;
                }

            }
            if ((evt.getX()<820 && evt.getX()>507&&(evt.getY()<715&&evt.getY()>256)&&portesoulevee==true)){
                door.stop();
                Scene scenes2=new Scene(salle2,1280,720);
                primaryStage.setScene(scenes2); //Passe a la scene suivante (salle 2)

            }
            if ((evt.getX()<1083 && evt.getX()>1054)&&(evt.getY()<476 && evt.getY()>450)){
                if (bouton1==3 && bouton2==1 && bouton3==2 && bouton4==4 && portesoulevee==false){
                    pt.play();
                    door.play();
                    portesoulevee=true;}
                else{
                    error.setVolume(0.5);
                    if(portesoulevee==false){
                    error.play();}
                    bouton1=0;
                    bouton2=0;
                    bouton3=0;
                    bouton4=0;
                    for(int i=0;i<5;i++){
                        if (salle1.getChildren().contains(boutons4[i])){
                            salle1.getChildren().remove(boutons4[i]);
                        }
                    }
                    for(int i=0;i<5;i++){
                        if (salle1.getChildren().contains(boutons1[i])){
                            salle1.getChildren().remove(boutons1[i]);
                        }
                    }
                    for(int i=0;i<5;i++){
                        if (salle1.getChildren().contains(boutons3[i])){
                            salle1.getChildren().remove(boutons3[i]);
                        }
                    }
                    for(int i=0;i<5;i++){
                        if (salle1.getChildren().contains(boutons2[i])){
                            salle1.getChildren().remove(boutons2[i]);
                        }
                    }

                }
            }
        });

        // DETECTIONS SOURIS SALLE 2
        salle2.setOnMouseClicked((evt) -> {
            if ((evt.getX()<1203 && evt.getX()>1049)&&(evt.getY()<366 && evt.getY()>237)&& ordizoom==false){
                ordizoom=true;
                salle2.getChildren().add(vlv1);
                salle2.getChildren().add(start);
                tambour.setVolume(0.5);
                tambour.play();
                vuelv1=true;
            }
            if (lv3rdy==true&&(evt.getX() < 705 && evt.getX() > 573) && (evt.getY()<382 && evt.getY() > 284)){
                door.stop();
                Scene scenes3=new Scene(salle3,1280,720);
                primaryStage.setScene(scenes3); //Passe a la scene suivante (salle 3)

            }
        });
        salle2.setOnMouseMoved((evt) -> {
            if (vuelv1==true) {
                if (vuelv1 == true && (evt.getX() < 663 && evt.getX() > 599) && (evt.getY() < 504 && evt.getY() > 487)) {
                    if (tambourjoué == false) {
                        tambour.play();
                        tambourjoué = true;
                    }
                    text2.setText("Go to the green square without touching the walls");
                    instructmsg = true;
                }
                if (instructmsg == true && !((evt.getX() < 663 && evt.getX() > 599) && (evt.getY() < 504 && evt.getY() > 487) ||
                        (evt.getX() < 617 && evt.getX() > 586 && evt.getY() < 352 && evt.getY() > 245) ||
                        (evt.getX() < 781 && evt.getX() > 585 && evt.getY() < 354 && evt.getY() > 325) ||
                        (evt.getX() < 781 && evt.getX() > 750 && evt.getY() < 454 && evt.getY() > 324) ||
                        (evt.getX() < 781 && evt.getX() > 646 && evt.getY() < 456 && evt.getY() > 428) ||
                        (evt.getX() < 648 && evt.getX() > 615 && evt.getY() < 501 && evt.getY() > 429) ||
                        (evt.getX() < 663 && evt.getX() > 599 && evt.getY() < 504 && evt.getY() > 484))) {
                    tambour.play();
                    tambourjoué = false;
                    instructmsg = false;
                    text2.setText("Put the mouse in the \"START\" rectangle");
                }
                if (instructmsg == true && (evt.getX() < 615 && evt.getX() > 587 && evt.getY() < 262 && evt.getY() > 245)) {
                    vuelv1 = false;
                    vuelv2=true;
                    tambour.play();
                    salle2.getChildren().remove(vlv1);
                    salle2.getChildren().add(vlv2);
                    text2.setText("Put the mouse in the \"START\" rectangle");
                }
            }
            if (vuelv2==true) {
                if ((evt.getX() < 666 && evt.getX() > 603) && (evt.getY() < 509 && evt.getY() > 490)) {
                    if (tambourjoué == false) {
                        tambour.play();
                        tambourjoué = true;
                    }
                    text2.setText("Go to the green square without touching the walls");
                    instructmsg = true;
                }
                if (instructmsg == true && !((evt.getX() < 528 && evt.getX() > 503) && (evt.getY() < 367 && evt.getY() > 247) ||
                        (evt.getX() < 601 && evt.getX() > 501 && evt.getY() < 367 && evt.getY() > 349) ||
                        (evt.getX() < 602 && evt.getX() > 569 && evt.getY() < 383 && evt.getY() > 292) ||
                        (evt.getX() < 688 && evt.getX() > 566 && evt.getY() < 308 && evt.getY() > 292) ||
                        (evt.getX() < 690 && evt.getX() > 657 && evt.getY() < 379 && evt.getY() > 289) ||
                        (evt.getX() < 892 && evt.getX() > 863 && evt.getY() < 394 && evt.getY() > 283) ||
                        (evt.getX() < 892 && evt.getX() > 807 && evt.getY() < 299 && evt.getY() > 285) ||
                        (evt.getX() < 830 && evt.getX() > 807 && evt.getY() < 380 && evt.getY() > 286) ||
                        (evt.getX() < 830 && evt.getX() > 656 && evt.getY() < 381 && evt.getY() > 353) ||
                        (evt.getX() < 737 && evt.getX() > 706 && evt.getY() < 453 && evt.getY() > 353) ||
                        (evt.getX() < 738 && evt.getX() > 622 && evt.getY() < 454 && evt.getY() > 432) ||
                        (evt.getX() < 650 && evt.getX() > 620 && evt.getY() < 505 && evt.getY() > 433) ||
                        (evt.getX() < 666 && evt.getX() > 603 && evt.getY() < 509 && evt.getY() > 490))) {
                    tambour.play();
                    tambourjoué = false;
                    instructmsg = false;
                    text2.setText("Put the mouse in the \"START\" rectangle");
                }
                if (instructmsg == true && (evt.getX() < 526 && evt.getX() > 503) && (evt.getY() < 265 && evt.getY() > 246)) {
                    salle2.getChildren().remove(text2);
                    salle2.getChildren().remove(vlv2);
                    vuelv2=false;
                    pt2.play();
                    door.play();
                    salle2.getChildren().remove(text2);
                    lv3rdy=true;
                }
            }
        });

        // FIN DETECTIONS SOURIS S2
        //DETECTION SOURIS S3
        salle3.setOnMouseClicked((evt) -> {
            System.out.println("Mouse at :" + evt.getX() + "," + evt.getY());
            if (((evt.getX() < 512 && evt.getX() > 362) && (evt.getY() < 199 && evt.getY() > 110)) && tabper == false && !(evt.getX() < 386 && evt.getX() > 325 && evt.getY() < 184 && evt.getY() > 144)) {
                for (int i = 0; i < 4; i++) {
                    if (salle3.getChildren().contains(becs[i])) {
                        salle3.getChildren().remove(becs[i]);
                    }
                }
                salle3.getChildren().add(period);
                papier.play();
                tabper = true;
            }
            if (!(evt.getX() < 1050 && evt.getX() > 201 && evt.getY() < 582 && evt.getY() > 101) && tabper == true) {
                tabper = false;
                salle3.getChildren().remove(period);
                papierev.play();
                if(becherplace==true) {
                    salle3.getChildren().add(becs[etatbecher]);
                }
            }
            if (evt.getX() < 899 && evt.getX() > 766 && evt.getY() < 556 && evt.getY() > 430 && noticeup == false) {
                for (int i = 0; i < 4; i++) {
                    if (salle3.getChildren().contains(becs[i])) {
                        salle3.getChildren().remove(becs[i]);
                    }
                }
                noticeup = true;
                salle3.getChildren().add(notice);
                papier.play();
            }
            if (!(evt.getX() < 894 && evt.getX() > 497 && evt.getY() < 676 && evt.getY() > 140) && noticeup == true) {
                noticeup = false;
                salle3.getChildren().remove(notice);
                papierev.play();
                if(becherplace==true) {
                    salle3.getChildren().add(becs[etatbecher]);
                }
            }
            if (evt.getX() < 996 && evt.getX() > 964 && evt.getY() < 176 && evt.getY() > 128 && becherplace == false) {
                salle3.getChildren().remove(becherz);
                salle3.getChildren().add(Becher);
                becherplace = true;
            }
            if (becherplace == true && lockpot == false) {
                for (int i = 0; i < 4; i++) {
                        if (salle3.getChildren().contains(becs[i])) {
                            salle3.getChildren().remove(becs[i]);
                        }
                }
                if ((evt.getX() < 386 && evt.getX() > 325 && evt.getY() < 184 && evt.getY() > 144) ||
                        (evt.getX() < 772 && evt.getX() > 693 && evt.getY() < 337 && evt.getY() > 143)) {
                    if (etatbecher == 3) {
                        if (!(bec[0] == "chlorure" && bec[1] == "potassium" && bec[2] == "mercure")) {
                            etatbecher = 0;
                        } else {
                            lockpot = true;
                            correct.play();
                        }
                    }
                    bec[etatbecher] = "zebi";
                    etatbecher += 1;
                    if (etatbecher == 3) {
                        if (!(bec[0] == "chlorure" && bec[1] == "potassium" && bec[2] == "mercure")) {
                            etatbecher = 0;
                            error.play();
                        } else {
                            lockpot = true;
                            correct.play();
                        }
                    }
                }
                if (evt.getX() < 883 && evt.getX() > 781 && evt.getY() < 429 && evt.getY() > 360) {
                    if (etatbecher == 3) {
                        if (!(bec[0] == "chlorure" && bec[1] == "potassium" && bec[2] == "mercure")) {
                            etatbecher = 0;
                        } else {
                            lockpot = true;
                            correct.play();
                        }
                    }
                    bec[etatbecher] = "mercure";
                    etatbecher += 1;
                    if (etatbecher == 3) {
                        if (!(bec[0] == "chlorure" && bec[1] == "potassium" && bec[2] == "mercure")) {
                            etatbecher = 0;
                            error.play();
                        } else {
                            lockpot = true;
                            correct.play();
                        }
                    }
                }
                if (evt.getX() < 995 && evt.getX() > 923 && evt.getY() < 433 && evt.getY() > 370) {
                    if (etatbecher == 3) {
                        if (!(bec[0] == "chlorure" && bec[1] == "potassium" && bec[2] == "mercure")) {
                            etatbecher = 0;
                        } else {
                            lockpot = true;
                            correct.play();
                        }
                    }
                    bec[etatbecher] = "potassium";
                    etatbecher += 1;
                    if (etatbecher == 3) {
                        if (!(bec[0] == "chlorure" && bec[1] == "potassium" && bec[2] == "mercure")) {
                            etatbecher = 0;
                            error.play();
                        } else {
                            lockpot = true;
                            correct.play();
                        }
                    }
                }
                if (evt.getX() < 883 && evt.getX() > 819 && evt.getY() < 324 && evt.getY() > 262) {
                    if (etatbecher == 3) {
                        if (!(bec[0] == "chlorure" && bec[1] == "potassium" && bec[2] == "mercure")) {
                            etatbecher = 0;
                        } else {
                            lockpot = true;
                            correct.play();
                        }
                    }
                    bec[etatbecher] = "chlorure";
                    etatbecher += 1;
                    if (etatbecher == 3) {
                        if (!(bec[0] == "chlorure" && bec[1] == "potassium" && bec[2] == "mercure")) {
                            etatbecher = 0;
                            error.play();
                        } else {
                            lockpot = true;
                            correct.play();
                        }
                    }
            }
                if(noticeup==false && tabper==false) {
                    salle3.getChildren().add(becs[etatbecher]);
                }
        }
            if (lockpot == true && (evt.getX() < 1260 && evt.getX() > 1170 && evt.getY() < 548 && evt.getY() > 438)) {
                for (int i = 0; i < 4; i++) {
                    if (salle3.getChildren().contains(becs[i])) {
                        salle3.getChildren().remove(becs[i]);
                    }
                }
                salle3.getChildren().add(potionf);
                correct.play();
                finale=true;
            }
            if(finale==true && (evt.getX() < 994 && evt.getX() > 883 && evt.getY() < 599 && evt.getY() > 430)){
             System.out.println("GG");
            }
        });
    }
    public static void main(String[] args) {
        launch(args);
    }
}