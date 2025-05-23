package com.enonic.xp.app.contentstudio.json.schema.xdata;

import java.time.Instant;

import com.google.common.base.Preconditions;

import com.enonic.xp.app.contentstudio.json.ItemJson;
import com.enonic.xp.app.contentstudio.json.form.FormJson;
import com.enonic.xp.app.contentstudio.rest.resource.schema.content.LocaleMessageResolver;
import com.enonic.xp.app.contentstudio.rest.resource.schema.mixin.InlineMixinResolver;
import com.enonic.xp.schema.xdata.XData;

import static com.google.common.base.Strings.nullToEmpty;

public class XDataJson
    implements ItemJson
{
    private final XData xData;

    private final Boolean isOptional;

    private final LocaleMessageResolver localeMessageResolver;

    private final InlineMixinResolver inlineMixinResolver;

    public XDataJson( final Builder builder )
    {
        Preconditions.checkNotNull( builder.localeMessageResolver );
        Preconditions.checkNotNull( builder.inlineMixinResolver );

        this.xData = builder.xData;
        this.isOptional = builder.isOptional;
        this.localeMessageResolver = builder.localeMessageResolver;
        this.inlineMixinResolver = builder.inlineMixinResolver;
    }

    public static Builder create()
    {
        return new Builder();
    }

    public String getName()
    {
        return xData.getName() != null ? xData.getName().toString() : null;
    }

    public String getDisplayName()
    {
        if ( !nullToEmpty( xData.getDisplayNameI18nKey() ).isBlank() )
        {
            return localeMessageResolver.localizeMessage( xData.getDisplayNameI18nKey(), xData.getDisplayName() );
        }
        else
        {
            return xData.getDisplayName();
        }
    }

    public String getDescription()
    {
        if ( !nullToEmpty( xData.getDescriptionI18nKey() ).isBlank() )
        {
            return localeMessageResolver.localizeMessage( xData.getDescriptionI18nKey(), xData.getDescription() );
        }
        else
        {
            return xData.getDescription();
        }
    }

    public Instant getCreatedTime()
    {
        return xData.getCreatedTime();
    }

    public Instant getModifiedTime()
    {
        return xData.getModifiedTime();
    }

    public FormJson getForm()
    {
        return new FormJson( xData.getForm(), this.localeMessageResolver, this.inlineMixinResolver );
    }

    public String getCreator()
    {
        return xData.getCreator() != null ? xData.getCreator().toString() : null;
    }

    public String getModifier()
    {
        return xData.getModifier() != null ? xData.getModifier().toString() : null;
    }

    public Boolean getIsOptional()
    {
        return isOptional;
    }

    @Override
    public boolean getDeletable()
    {
        return false;
    }

    @Override
    public boolean getEditable()
    {
        return false;
    }

    public static final class Builder
    {
        private XData xData;

        private Boolean isOptional = false;

        private LocaleMessageResolver localeMessageResolver;

        private InlineMixinResolver inlineMixinResolver;

        private Builder()
        {
        }

        public Builder setXData( final XData xData )
        {
            this.xData = xData;
            return this;
        }

        public Builder setOptional( final Boolean optional )
        {
            isOptional = optional;
            return this;
        }

        public Builder setLocaleMessageResolver( final LocaleMessageResolver localeMessageResolver )
        {
            this.localeMessageResolver = localeMessageResolver;
            return this;
        }

        public Builder setInlineMixinResolver( final InlineMixinResolver inlineMixinResolver )
        {
            this.inlineMixinResolver = inlineMixinResolver;
            return this;
        }

        private void validate()
        {
            Preconditions.checkNotNull( localeMessageResolver );
            Preconditions.checkNotNull( inlineMixinResolver );
        }

        public XDataJson build()
        {
            validate();
            return new XDataJson( this );
        }
    }

}
