package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.DbActivity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "Subscription")
public class DbSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    UUID subscriptionid;

    @ManyToOne
    private DbActivity activity;

    @Embedded
    private Name name;

    @Embedded
    private Email email;

    public static DbSubscription of(UUID subscriptionid, DbActivity activity, Name name, Email email) {
        return new DbSubscription(null, subscriptionid, activity, name, email);
    }

}
