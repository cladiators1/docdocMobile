/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.Gui.zohra;

import com.codename1.ui.Form;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Entities.Reponse;
import com.mycompany.myapp.Services.ReponseService;
import java.util.ArrayList;
import com.codename1.ui.Dialog;
import com.mycompany.myapp.Entities.Question;
import com.mycompany.myapp.Entities.User;

/**
 *
 * @author user
 */
public class AfficherUneQuestion extends Form{
    Form current;

        public AfficherUneQuestion(Reponse u,Question q,User user) {
            setTitle("Question");
            Container questionCo=new Container(BoxLayout.yCenter());
            Label lTitreq = new Label("Titre : "+q.getTitre(),"RedLabel");
                            Label ldusercatq = new Label("categorie : "+q.getCatMed().getNom()+" nom user: "+q.getUser().getNom(),"RedLabel");
                           // Label lUser = new Label("cette question est ajoutée par: "+fi.getUser().getNom()+" "+fi.getUser().getPrenom(),"SmallLabel");
                            Label lSymptomesq = new Label("Symptomes : "+q.getSymptomes(),"RedLabel");
                            Label ldetailsq = new Label("Details poids: "+q.getPoids()+" Taille: "+q.getTaille(),"SmallLabel");
                            lTitreq.getAllStyles().setFgColor(0xf15f5f);
                            questionCo.add(lTitreq);
                            //ct.add(lUser);
                            questionCo.add(ldetailsq);
                            questionCo.add(lSymptomesq);
                            questionCo.add(ldusercatq);
                            Label separator = new Label("","Separator");
                       questionCo.add(separator);
                       add(questionCo);
            
            

          Container co=new Container(BoxLayout.xCenter());
                    ArrayList <Reponse> questions = new ArrayList();
                        ReponseService sa =new ReponseService();
                            questions=sa.getAll(q);
                             for (Reponse fi : questions) {
                            Container ct = new Container(BoxLayout.y());
                            Label lTitre = new Label("Titre : "+fi.getDescription(),"RedLabel");
                            Label lUser = new Label("nom utilisateur : "+fi.getUser().getNom()+" "+fi.getUser().getPrenom(),"RedLabel");
                            lTitre.getAllStyles().setFgColor(0xf15f5f);
                            ct.add(lUser);                            
                            ct.add(lTitre);
                            Button Supp = new Button("Supprimer");
                            Button modfier = new Button("Modfier");
                            Supp.setEnabled(false);
                            modfier.setEnabled(false);
                            if(fi.getUser().getId()==user.getId())
                            {
                                Supp.setEnabled(true);
                                modfier.setEnabled(true);
                            }
                            
                            
                             modfier.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {              
                if (Dialog.show("Confirmation", "Voulez vous Modifier cette reponse ?", "Modifier ", "Annuler")) {
                             //new EditQuestion(current, fi).show();
            }    
            }
        });
                            Supp.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {              
                if (Dialog.show("Confirmation", "Voulez vous Supprimer cette reponse ?", "Supprimer", "Annuler")) {
                Reponse t = new Reponse();
                t.setId(fi.getId());
                        if( ReponseService.getInstance().delete(t)){
                            {
                                Dialog.show("Success","supprimer",new Command("OK"));
               
                                new AfficherUneQuestion(u,q,user).show();
                            }
                   
                }
            }    
            }
        });
                       ct.add(modfier);
                       ct.add(Supp);
                       Label separator1 = new Label("","Separator");
                       ct.add(separator1);
                       add(ct);
                             }
        //Tool Bar
       // getToolbar().addCommandToSideMenu("Home", null, e -> new MenuCategoryChambre().show());
        //getToolbar().addCommandToSideMenu("Consultations", null, e -> new MenuChambre(u).show());
        //getToolbar().addCommandToSideMenu("Reponses", null, e -> new MenuCategory(u).show());*/
    
        }
        
}