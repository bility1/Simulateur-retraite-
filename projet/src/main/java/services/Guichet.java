package services;

import entities.CentreInteret;
import entities.Groupe;
import entities.Membre;
import entities.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
public class Guichet {

    @PersistenceContext
    EntityManager em;
//    private List<Membre> membres;
//    private List<Message> messages;
//    private List<Groupe> groupes;
//    private List<CentreInteret> centresInteret;

    public Guichet() {
        /**/
    }
    @Transactional
    public Membre getMembre(String login) {
        return  em.find(Membre.class,login);
        /*for (Membre m : membres) {
            if (m.getLogin().equals(login)) {
                return m;
            }
        }
        return null;*/
    }
    @Transactional
    public void jeuDonne(){

        // centres d'intérêts
        CentreInteret ci1=new CentreInteret();
        ci1.setIdCI(1);
        ci1.setIntituleCI("cuisine");
        em.persist(ci1);
        CentreInteret ci2=new CentreInteret();
        ci2.setIdCI(2);
        ci2.setIntituleCI("shopping");
        em.persist(ci2);
        CentreInteret ci3=new CentreInteret();
        ci3=new CentreInteret();
        ci3.setIdCI(3);
        ci3.setIntituleCI("sport");
        em.persist(ci3);

        // groupes
        Groupe gr1=new Groupe();
        gr1.setIdGroupe(1);
        gr1.setNomGroupe("Joueurs de mots");
        em.persist(gr1);

        Groupe gr2=new Groupe();
        gr2.setIdGroupe(2);
        gr2.setNomGroupe("Compost et lombric");
        em.persist(gr2);

        // Membres
        Membre m1=new Membre();
        m1.setLogin("Albert");
        m1.setPasswd("Albert");
        m1.getInterets().add(ci1);
        m1.getGroupes().add(gr1);

        em.persist(m1);

        Membre m2=new Membre();
        m2.setLogin("Jacqueline");
        m2.setPasswd("Jacqueline");
        m2.getInterets().add(ci2);
        m2.getGroupes().add(gr2);
        em.persist(m2);

        Membre m3=new Membre();
        m3.setLogin("Kenny");
        m3.setPasswd("Kenny");
        m3.getGroupes().add(gr2);
        em.persist(m3);

        // Amis
        m1.getAmis().add(m2);
        m2.getAmis().add(m1);
        m2.getAmis().add(m3);
        m3.getAmis().add(m2);

        // messages
        Message msg1=new Message();
        msg1.setDateMessage(new GregorianCalendar().getTime());
        msg1.setTexteMessage("Il fait beau et chaud");
        msg1.setAuteur(m1);
        em.persist(msg1);
        m1.getPublications().add(msg1);
        gr1.getPublisGroupe().add(msg1);


        Message msg2=new Message();
        msg2.setDateMessage(new GregorianCalendar().getTime());
        msg2.setTexteMessage("La vie des mots m'émeut");
        msg2.setReference(msg1);
        msg2.setAuteur(m2);
        em.persist(msg2);
        m2.getPublications().add(msg2);


        Message msg3=new Message();
        msg3.setDateMessage(new GregorianCalendar().getTime());
        msg3.setTexteMessage("un ver ça va...");
        m3.getPublications().add(msg3);
        msg3.setAuteur(m3);
        em.persist(msg3);
        gr2.getPublisGroupe().add(msg3);

    }
    @Transactional
    public Collection<Message> getMessages(Membre membres) {
        Collection<Message> mc = new ArrayList<>();
        for (Membre mm:membres.getAmis()){
            for(Message msg:mm.getPublications()){
                mc.add(msg);
            }
        }
        for(Groupe groupe:membres.getGroupes()){
            for(Message msg :groupe.getPublisGroupe()){
                mc.add(msg);
            }
        }

        return mc;
    }
//    @Transactional
//    public Collection<Message> getMessages() {
//        EntityGraph<Message> entityGraph = em.createEntityGraph(Message.class);
//        entityGraph.addSubgraph("reference");
//        Query query = em.createQuery("from Message m");
//        query.setHint("javax.persistance.loadgraph",entityGraph);
//        return query.getResultList();
//    }




    public void publier(String courant, String message, int grid) {
        Membre m =getMembre(courant);
        Message msg=new Message();
        msg.setTexteMessage(message);
        msg.setAuteur(m);
        msg.setDateMessage(new GregorianCalendar().getTime());
        m.getPublications().add(msg);
       // messages.add(msg);
        if (grid != -1) {
            for (Groupe gr:getGroupes()) {
                if (gr.getIdGroupe()==grid) {
                    gr.getPublisGroupe().add(msg);
                    break;
                }
            }
        }
    }

    public List<Groupe> getGroupes() {

        return em.createQuery("from Groupe g").getResultList();
    }
    @Transactional
    public List<Membre> getMembres(Membre membres) {

        List<Membre> mc = new ArrayList<>();
        for (Membre mm:membres.getAmis()){
            for (Membre me:mm.getAmis()){
                if(mm.getAmis().contains(me)==true && mm.getAmis().contains(mm)==false){
                    mc.add(me);
                }
            }
        }

        return mc;
    }

    public void relier(String courant, String membre) {
        Membre m1=getMembre(courant);
        Membre m2=getMembre(membre);
        m1.getAmis().add(m2);
        m2.getAmis().add(m1);

    }
}
