package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.subscription.DbSubscription;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityDate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.TotalNumberOfSeats;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Value
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "Activity")
public class DbActivity {

    @Id
    @GeneratedValue
    Long id;

    UUID activityid;

    @OneToMany
    List<DbSubscription> subscriptionList;

    @Embedded
    Description description;

    @Embedded
    Price price;

    @Embedded
    ActivityDate activityDate;

    @Embedded
    TotalNumberOfSeats totalNumberOfSeats;

    @Embedded
    AgendaContentRef agendaContentRef;

    public static DbActivity of(UUID activityid, List<DbSubscription> subscriptionList, Description description, Price price, ActivityDate activityDate, TotalNumberOfSeats totalNumberOfSeats, AgendaContentRef agendaContentRef) {
        DbActivity dbActivity = new DbActivity(null,
                activityid,
                subscriptionList,
                description,
                price,
                activityDate,
                totalNumberOfSeats,
                agendaContentRef
                );
        return dbActivity;
    }
}
