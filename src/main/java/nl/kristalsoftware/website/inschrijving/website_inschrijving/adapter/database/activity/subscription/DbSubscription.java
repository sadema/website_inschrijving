package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.DbActivity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Note;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionRef;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "Subscription")
public class DbSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Embedded
    private SubscriptionRef subscriptionRef;

    @ManyToOne
    private DbActivity activity;

    @Embedded
    private Name name;

    @Embedded
    private Email email;

    @Embedded
    private Note note;

    public static DbSubscription of(SubscriptionRef subscriptionRef, DbActivity activity, Name name, Email email, Note note) {
        return new DbSubscription(null, subscriptionRef, activity, name, email, note);
    }

}
